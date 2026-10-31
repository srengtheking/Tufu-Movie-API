package com.tufu.movie.users.mapper;

import com.tufu.movie.users.dto.UserResponse;
import com.tufu.movie.users.entity.User;

public class UserMapper {
	
	public static UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setIsDelete(user.getIsDelete());
        response.setIsLocked(user.getIsLocked());
        response.setFailedLogin(user.getFailedLogin());
        response.setEnable(user.getEnable());
        response.setCreateDate(user.getCreateDate());
        response.setLastDateUpdatePassword(
                user.getLastDateUpdatePassword());

        if (user.getRole() != null) {
            response.setRoleId(user.getRole().getId());
            response.setRoleName(user.getRole().getName());
        }

        return response;
    }

}
