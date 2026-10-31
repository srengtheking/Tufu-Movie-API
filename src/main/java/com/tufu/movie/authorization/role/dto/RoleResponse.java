package com.tufu.movie.authorization.role.dto;

import java.util.List;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse {

    private Long id;
    private String name;
    private String description;
    private List<PermissionResponse> permissions;

    public RoleResponse() {
    }
    
    public RoleResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public RoleResponse(Long id, String name, String description, List<PermissionResponse> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionResponse> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionResponse> permissions) {
        this.permissions = permissions;
    }
}
