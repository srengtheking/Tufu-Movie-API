package com.tufu.movie.users.dto;

import java.time.LocalDate;

public class UserResponse {
	
    private Long id;
    private String username;
    private String email;
    private Boolean isDelete;
    private Boolean isLocked;
    private Long failedLogin;
    private Boolean enable;
    private LocalDate createDate;
    private LocalDate lastDateUpdatePassword;
    private Long roleId;
    private String roleName;
    
    public UserResponse() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Long getFailedLogin() {
		return failedLogin;
	}

	public void setFailedLogin(Long failedLogin) {
		this.failedLogin = failedLogin;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getLastDateUpdatePassword() {
		return lastDateUpdatePassword;
	}

	public void setLastDateUpdatePassword(LocalDate lastDateUpdatePassword) {
		this.lastDateUpdatePassword = lastDateUpdatePassword;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
