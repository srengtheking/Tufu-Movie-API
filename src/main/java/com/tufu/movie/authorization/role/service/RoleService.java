package com.tufu.movie.authorization.role.service;

import java.util.List;

import com.tufu.movie.authorization.role.dto.RoleRequest;
import com.tufu.movie.authorization.role.dto.RoleResponse;

public interface RoleService {

    RoleResponse create(RoleRequest request);

    List<RoleResponse> findAll();

    RoleResponse findById(Long id);

    RoleResponse update(Long id, RoleRequest request);

    void delete(Long id);

    RoleResponse assignOrunassignPermission(Long roleId, Long permissionId);
}
