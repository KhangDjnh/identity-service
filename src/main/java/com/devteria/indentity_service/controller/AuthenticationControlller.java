package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.*;
import com.devteria.indentity_service.dto.resqonse.AuthenticationResponse;
import com.devteria.indentity_service.dto.resqonse.IntrospectResponse;
import com.devteria.indentity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationControlller {
    final AuthenticationService authencationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authencationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authencationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .message("Success")
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<String> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authencationService.logout(request);
        return ApiResponse.<String>builder()
                .message("Success")
                .code(1000)
                .result("Logout success")
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = authencationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Success")
                .code(1000)
                .result(result)
                .build();
    }
}
