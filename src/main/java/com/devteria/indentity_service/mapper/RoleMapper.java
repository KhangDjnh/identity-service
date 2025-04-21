package com.devteria.indentity_service.mapper;

import com.devteria.indentity_service.dto.request.RoleRequest;
import com.devteria.indentity_service.dto.resqonse.RoleResponse;
import com.devteria.indentity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
