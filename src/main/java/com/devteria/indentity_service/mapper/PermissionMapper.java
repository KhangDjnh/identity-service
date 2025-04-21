package com.devteria.indentity_service.mapper;


import com.devteria.indentity_service.dto.request.PermissionRequest;
import com.devteria.indentity_service.dto.resqonse.PermissionResponse;
import com.devteria.indentity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
