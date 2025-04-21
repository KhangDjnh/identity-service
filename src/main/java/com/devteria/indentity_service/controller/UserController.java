package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.resqonse.UserResponse;
import com.devteria.indentity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/users")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(1000);
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping("/users")
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<UserResponse>>builder()
                .message("Success")
                .code(1000)
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/users/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId) {

        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("Success")
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/users/myInfo")
    ApiResponse<UserResponse> getMyInfo() {

        return ApiResponse.<UserResponse>builder()
                .message("Success")
                .code(1000)
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/users/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("Success")
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/users/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .message("Success")
                .code(1000)
                .result("User deleted")
                .build();
    }

    @DeleteMapping("/users")
    ApiResponse<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ApiResponse.<String>builder()
                .message("Success")
                .code(1000)
                .result("All Users deleted")
                .build();
    }
}
