package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.resqonse.UserResponse;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.exception.AppException;
import com.devteria.indentity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private User user;

    @BeforeEach
    void initData() {
        LocalDate dob = LocalDate.of(1990, 1, 1);

        request = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();

        user = User.builder()
                .id("cf0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);
        // THEN

        Assertions.assertThat(response.getDob()).isEqualTo("1990-01-01");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class,
                () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode())
                .isEqualTo(1002);
    }
}
