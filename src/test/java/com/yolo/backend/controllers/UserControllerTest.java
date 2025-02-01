package com.yolo.backend.controllers;

import com.yolo.backend.domain.User;
import com.yolo.backend.domain.enums.UserType;
import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new User()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUserById_UserFound() throws Exception {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId));
    }

    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        Long userId = 999L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found."));
    }

    @Test
    public void testSaveUser() throws Exception {
        UserDTO userDTO = new UserDTO(
                "teste save user controller",
                "+5511999999999",
                "teste@gmail.com",
                UserType.OPERATOR,
                LocalDate.now()
        );

        User user = new User();
        user.setId(1L);
        when(userService.saveUser(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    public void testUpdateUser_UserFound() throws Exception {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(
                "teste update user controller",
                "+5511999999999",
                "teste@gmail.com",
                UserType.OPERATOR,
                LocalDate.now()
        );

        User user = new User();
        user.setId(userId);
        when(userService.updateUser(eq(userId), any(UserDTO.class))).thenReturn(Optional.of(user));

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    public void testDeleteUserById_UserFound() throws Exception {
        Long userId = 1L;
        when(userService.deleteUserById(userId)).thenReturn(true);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully."));
    }

    @Test
    public void testDeleteUserById_UserNotFound() throws Exception {
        Long userId = 999L;
        when(userService.deleteUserById(userId)).thenReturn(false);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found."));
    }

}
