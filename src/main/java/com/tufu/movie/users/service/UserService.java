package com.tufu.movie.users.service;

import java.util.List;

import com.tufu.movie.users.dto.UserRequest;
import com.tufu.movie.users.dto.UserResponse;

public interface UserService {
	
    UserResponse create(UserRequest request);

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);

}
