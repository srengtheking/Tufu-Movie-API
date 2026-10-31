package com.tufu.movie.authorization.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tufu.movie.authorization.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
