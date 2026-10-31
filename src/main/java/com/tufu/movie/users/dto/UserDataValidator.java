package com.tufu.movie.users.dto;


import com.tufu.movie.users.exception.InvalidJsonException;

import org.springframework.stereotype.Component;

@Component
public final class UserDataValidator {

	public void validateForCreate(final UserRequest request) {
		
        if (request == null) {
            throw new InvalidJsonException("JSON request cannot be empty");
        }

        if (request.getUsername() == null || request.getUsername().isBlank()) {
            throw new InvalidJsonException("Username cannot be empty");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new InvalidJsonException("Email cannot be empty");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new InvalidJsonException("Password cannot be empty");
        }
    }
}
