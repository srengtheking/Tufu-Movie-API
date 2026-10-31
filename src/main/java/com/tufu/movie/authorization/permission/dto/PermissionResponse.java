package com.tufu.movie.authorization.permission.dto;

public class PermissionResponse {

    private Long id;
    private String grouping;
    private String code;
    private String entityName;
    private String actionName;

    public PermissionResponse() {
    }

    public PermissionResponse(Long id, String grouping, String code, String entityName, String actionName) {
        this.id = id;
        this.grouping = grouping;
        this.code = code;
        this.entityName = entityName;
        this.actionName = actionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
