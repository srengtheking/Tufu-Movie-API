package com.tufu.movie.users.entity;

import java.time.LocalDate;

import com.tufu.movie.authorization.role.entity.Role;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_delete")
    private Boolean isDelete;
    
    @Column(name = "is_locked")
    private Boolean isLocked;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    
    @Column(name = "failed_login")
    private Long failedLogin;
    
    @Column(name = "enable")
    private Boolean enable;
    
    @Column(name = "create_date")
    private LocalDate createDate;
    
    @Column(name = "last_date_update_password")
    private LocalDate lastDateUpdatePassword;
    
    public User() {
    	
    }

    public User(Long id, String username, String email, String password, Boolean isDelete,
    		Boolean isLocked, Role role, Long failedLogin, Boolean enable,
    		LocalDate createDate, LocalDate lastDateUpdatePassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isDelete = isDelete;
        this.isLocked = isLocked;
        this.role = role;
        this.failedLogin = failedLogin;
        this.enable = enable;
        this.createDate = createDate;
        this.lastDateUpdatePassword = lastDateUpdatePassword;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	
}
