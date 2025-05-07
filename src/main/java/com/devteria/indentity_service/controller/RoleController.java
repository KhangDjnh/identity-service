package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.RoleRequest;
import com.devteria.indentity_service.dto.resqonse.RoleResponse;
import com.devteria.indentity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;
    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        var result = roleService.createRole(request);
        return ApiResponse.<RoleResponse>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoll() {
        var result = roleService.getAllRole();
        return ApiResponse.<List<RoleResponse>>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
    @DeleteMapping("/{role}")
    ApiResponse<String> deleteRole(@PathVariable String role) {
        roleService.deleteRole(role);
        return ApiResponse.<String>builder()
                .message("Success")
                .code(1000)
                .result("Roles deleted")
                .build();
    }
}
