package com.tufu.movie.authorization.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;
import com.tufu.movie.authorization.permission.mapper.PermissionMapper;
import com.tufu.movie.authorization.permission.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAll() {
        return permissionRepository.findAll()
                .stream()
                .map(PermissionMapper::toResponse)
                .toList();
    }
}
