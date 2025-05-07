package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.resqonse.UserResponse;
import com.devteria.indentity_service.entity.Role;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.enums.Roles;
import com.devteria.indentity_service.exception.AppExceprion;
import com.devteria.indentity_service.exception.ErrorCode;
import com.devteria.indentity_service.mapper.UserMapper;
import com.devteria.indentity_service.repository.RoleRepository;
import com.devteria.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppExceprion(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(Roles.USER.name())
                .orElseThrow(() -> new AppExceprion(ErrorCode.ROLE_NOT_FOUND)));
        user.setRoles(roles);
        user.setActive(true);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(userRepository.findByIdAndIsActive(userId, true)
                .orElseThrow(() -> new AppExceprion(ErrorCode.USERNAME_NOT_EXIST)));
    }
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppExceprion(ErrorCode.USERNAME_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppExceprion(ErrorCode.USERNAME_NOT_EXIST));
        userMapper.updateUser(user,request);
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        List<String> roleIds = request.getRoles();
        List<Role> roles = roleRepository.findAllById(roleIds);

        if (roles.size() != roleIds.size()) {
            throw new AppExceprion(ErrorCode.ROLE_NOT_FOUND);
        }

        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
    public void deleteUser(String userId) {
        User user = userRepository.findByIdAndIsActive(userId, true).orElseThrow(() -> new AppExceprion(ErrorCode.USERNAME_NOT_EXIST));
        user.setActive(false);
    }
    public void deleteAllUsers() {
        userRepository.deleteAllExceptAdmin();
    }
}
