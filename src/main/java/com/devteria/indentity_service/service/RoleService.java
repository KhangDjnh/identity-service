package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.RoleRequest;
import com.devteria.indentity_service.dto.resqonse.RoleResponse;
import com.devteria.indentity_service.mapper.RoleMapper;
import com.devteria.indentity_service.repository.PermissionRepositopry;
import com.devteria.indentity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepositopry permissionRepositopry;
    RoleMapper roleMapper;

    public RoleResponse createRole(@RequestBody RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepositopry.findAllById(request.getPermissions());
        log.info("permissions: {}", permissions);
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
    public List<RoleResponse> getAllRole() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }
    public void deleteRole(String roleName) {
        roleRepository.deleteById(roleName);
    }
}
