package com.tufu.movie.users.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tufu.movie.authorization.role.entity.Role;
import com.tufu.movie.authorization.role.repository.RoleRepository;
import com.tufu.movie.users.dto.UserDataValidator;
import com.tufu.movie.users.dto.UserRequest;
import com.tufu.movie.users.dto.UserResponse;
import com.tufu.movie.users.entity.User;
import com.tufu.movie.users.mapper.UserMapper;
import com.tufu.movie.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDataValidator userDataValidator;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository,final UserDataValidator userDataValidator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDataValidator = userDataValidator;
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
    	
    	this.userDataValidator.validateForCreate(request);
			
        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username, email and password are required");
        }

        if (userRepository.existsByUsernameAndIsDeleteFalse(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists");
        }

        if (userRepository.existsByEmailAndIsDeleteFalse(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setIsDelete(false);
        user.setIsLocked(false);
        user.setFailedLogin(0L);
        user.setEnable(true);
        user.setCreateDate(LocalDate.now());
        user.setLastDateUpdatePassword(LocalDate.now());

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Role not found"));

            user.setRole(role);
        }

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {

        return userRepository.findByIsDeleteFalse()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {

        User user = getActiveUser(id);

        return UserMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest request) {

        User user = getActiveUser(id);

        if (request.getUsername() != null
                && !request.getUsername().isBlank()
                && !request.getUsername().equals(user.getUsername())) {

            if (userRepository.existsByUsernameAndIsDeleteFalse(
                    request.getUsername())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Username already exists");
            }

            user.setUsername(request.getUsername());
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && !request.getEmail().equals(user.getEmail())) {

            if (userRepository.existsByEmailAndIsDeleteFalse(
                    request.getEmail())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Email already exists");
            }

            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null
                && !request.getPassword().isBlank()) {

            user.setPassword(
                    passwordEncoder.encode(request.getPassword()));

            user.setLastDateUpdatePassword(LocalDate.now());
        }

        if (request.getRoleId() != null) {

            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Role not found"));

            user.setRole(role);
        }

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long userId) {

        User user = getActiveUser(userId);

        // Soft delete
        user.setIsDelete(true);

        userRepository.save(user);
    }

    private User getActiveUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));

        if (Boolean.TRUE.equals(user.getIsDelete())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found");
        }

        return user;
    }

}
