package com.devteria.indentity_service.controller;


import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.PermissionRequest;
import com.devteria.indentity_service.dto.resqonse.PermissionResponse;
import com.devteria.indentity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        var result = permissionService.createPermission(request);
        return ApiResponse.<PermissionResponse>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermission() {
        var result = permissionService.getAllPermissions();
        return ApiResponse.<List<PermissionResponse>>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse<String> deletePermission(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        return ApiResponse.<String>builder()
                .message("Success")
                .code(1000)
                .result("Permission deleted")
                .build();
    }
}
