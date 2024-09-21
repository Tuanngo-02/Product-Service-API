package com.service.medicine.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.service.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private UserCreationRequest request;
    private UserResponse response;
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
                .id("57734753db0b")
                .firstName("tuan6")
                .lastName("tuan6")
                .username("tuan6")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(response);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"));
    }

    @Test
    void getUser_validRequest_success() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(response);

        List<UserResponse> userResponses = new ArrayList<>();
        Mockito.when(userService.getUser(PageRequest.of(0, 5)))
                .thenReturn((Page<UserResponse>) userResponses.stream().toList());

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateUser_validRequest_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        response.setFirstName("tuan7");
        Mockito.when(userService.updateUser("57734753db0b", updateRequest)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/57734753db0b")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(response)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void delete_validRequest_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mockito.doNothing().when(userService).deleteUser("57734753db0b");

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/57734753db0b")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
