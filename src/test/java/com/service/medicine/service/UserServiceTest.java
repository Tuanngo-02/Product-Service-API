package com.service.medicine.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.model.User;
import com.service.medicine.reponsitory.UserRepository;
import com.service.medicine.service.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    private UserCreationRequest request;
    private UserResponse response;
    private User user;
    private UserUpdateRequest updateRequest;

    private LocalDate dob;

    @BeforeEach
    void initDate() {
        dob = LocalDate.of(2004, 2, 2);
        request = UserCreationRequest.builder()
                .firstName("tuan6")
                .lastName("tuan6")
                .username("tuan6")
                .password("tuan6")
                .dob(dob)
                .build();
        updateRequest = UserUpdateRequest.builder()
                .firstName("tuan7")
                .lastName("tuan7")
                .password("tuan7")
                .dob(dob)
                .build();
        response = UserResponse.builder()
                .id("6f77d4fddd93")
                .firstName("tuan6")
                .lastName("tuan6")
                .username("tuan6")
                .dob(dob)
                .build();

        user = User.builder()
                .id("6f77d4fddd93")
                .firstName("tuan6")
                .lastName("tuan6")
                .username("tuan6")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // given
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString()))
                .thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        // when
        var response_test = userService.createUser(request);

        // then
        Assertions.assertThat(response_test.getId()).isEqualTo("6f77d4fddd93");
        Assertions.assertThat(response_test.getUsername()).isEqualTo("tuan6");
    }

    @Test
    void getUser_valid_success() {
        List<User> users = new ArrayList<User>();

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserResponse> userResponses = (List<UserResponse>) userService.getUser(PageRequest.of(0, 5));

        Assertions.assertThat(userResponses).isEqualTo(users);
    }

    @Test
    void updateUser_valid_success() {
        Mockito.when(userRepository.findById("6f77d4fddd93")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        var response_test = userService.updateUser("6f77d4fddd93", updateRequest);

        Assertions.assertThat(response_test.getFirstName()).isEqualTo("tuan7");
        Assertions.assertThat(response_test.getLastName()).isEqualTo("tuan7");
    }

    @Test
    void delete_valid_success() {
        userService.deleteUser("6f77d4fddd93");

        Mockito.doNothing().when(userRepository).deleteById(user.getId());
    }
}
