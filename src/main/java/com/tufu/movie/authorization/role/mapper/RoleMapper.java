package com.tufu.movie.authorization.role.mapper;

import java.util.List;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;
import com.tufu.movie.authorization.role.dto.RoleResponse;
import com.tufu.movie.authorization.role.entity.Role;

public final class RoleMapper {

    private RoleMapper() {
    }
    
    public static RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }

    public static RoleResponse toResponse(Role role, List<PermissionResponse> permissions) {
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription(),
                permissions
        );
    }
}
