package com.tufu.movie.authorization.role.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;
import com.tufu.movie.authorization.permission.entity.Permission;
import com.tufu.movie.authorization.permission.repository.PermissionRepository;
import com.tufu.movie.authorization.role.dto.RoleRequest;
import com.tufu.movie.authorization.role.dto.RoleResponse;
import com.tufu.movie.authorization.role.entity.Role;
import com.tufu.movie.authorization.role.mapper.RoleMapper;
import com.tufu.movie.authorization.role.repository.RoleRepository;
import com.tufu.movie.users.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public RoleResponse create(RoleRequest request) {
        validateRequest(request);
        ensureNameAvailable(request.getName(), null);

        Role role = new Role();
        role.setName(request.getName().trim());
        role.setDescription(request.getDescription());
        return RoleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse findById(Long id) {
        Role role = getRole(id);

        List<PermissionResponse> permissions = role.getPermissions().stream()
                .sorted(Comparator.comparing(Permission::getId))
                .map(permission -> new PermissionResponse(
                        permission.getId(),
                        permission.getGrouping(),
                        permission.getCode(),
                        permission.getEntityName(),
                        permission.getActionName()))
                .toList();

        return RoleMapper.toResponse(role, permissions);
    }

    @Override
    @Transactional
    public RoleResponse update(Long id, RoleRequest request) {
        validateRequest(request);
        Role role = getRole(id);
        ensureNameAvailable(request.getName(), id);

        role.setName(request.getName().trim());
        role.setDescription(request.getDescription());
        return RoleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Role role = getRole(id);
        if (userRepository.existsByRole_Id(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Role is assigned to one or more users");
        }
        roleRepository.delete(role);
    }

    @Override
    @Transactional
    public RoleResponse assignOrunassignPermission(Long roleId, Long permissionId) {
        Role role = getRole(roleId);
        Permission permission = getPermission(permissionId);
        boolean alreadyAssigned = role.getPermissions().stream()
                .anyMatch(existing -> permissionId.equals(existing.getId()));
        if (alreadyAssigned) {
            role.getPermissions().removeIf(existing -> permissionId.equals(existing.getId()));
        } else {
            role.getPermissions().add(permission);
        }
        return RoleMapper.toResponse(roleRepository.save(role));
    }

    private Role getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    private Permission getPermission(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
    }

    private void validateRequest(RoleRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role name is required");
        }
    }

    private void ensureNameAvailable(String name, Long currentRoleId) {
        String normalizedName = name.trim();
        boolean alreadyUsed = currentRoleId == null
                ? roleRepository.existsByName(normalizedName)
                : roleRepository.existsByNameAndIdNot(normalizedName, currentRoleId);

        if (alreadyUsed) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name already exists");
        }
    }
}
