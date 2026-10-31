package com.tufu.movie.authorization.permission.mapper;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;
import com.tufu.movie.authorization.permission.entity.Permission;

public final class PermissionMapper {

    private PermissionMapper() {
    }

    public static PermissionResponse toResponse(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getGrouping(),
                permission.getCode(),
                permission.getEntityName(),
                permission.getActionName());
    }
}
