package com.tufu.movie.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tufu.movie.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	
    List<User> findByIsDeleteFalse();

    boolean existsByUsernameAndIsDeleteFalse(String username);

    boolean existsByEmailAndIsDeleteFalse(String email);

    boolean existsByRole_Id(Long roleId);

}
