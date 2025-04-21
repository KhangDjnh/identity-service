package com.devteria.indentity_service.service;


import com.devteria.indentity_service.dto.request.PermissionRequest;
import com.devteria.indentity_service.dto.resqonse.PermissionResponse;
import com.devteria.indentity_service.entity.Permission;
import com.devteria.indentity_service.mapper.PermissionMapper;
import com.devteria.indentity_service.repository.PermissionRepositopry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepositopry permissionRepositopry;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission (PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permissionRepositopry.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepositopry.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }
    public void deletePermission(String permissionName) {
        permissionRepositopry.deleteById(permissionName);
    }
}

