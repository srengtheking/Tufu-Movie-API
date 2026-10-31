package com.tufu.movie.authorization.permission.service;


import java.util.List;

import com.tufu.movie.authorization.permission.dto.PermissionResponse;

public interface PermissionService {
	
	List<PermissionResponse> findAll(); 
}
