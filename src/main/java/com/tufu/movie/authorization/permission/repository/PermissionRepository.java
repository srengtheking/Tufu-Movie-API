package com.tufu.movie.authorization.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tufu.movie.authorization.permission.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
