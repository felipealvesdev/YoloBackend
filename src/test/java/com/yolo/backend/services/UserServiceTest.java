package com.yolo.backend.services;

import com.yolo.backend.domain.User;
import com.yolo.backend.domain.enums.UserType;
import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John");
    }

    @Test
    public void testGetUserById_UserFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(userId);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveUser() {
        UserDTO userDTO = new UserDTO(
                "teste save service",
                "+5511999999999",
                "teste@gmail.com",
                UserType.OPERATOR,
                LocalDate.now()
        );

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(userDTO);
        assertNotNull(result);
        assertEquals(user.getName(), result.getName());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        Long userId = 999L;
        UserDTO userDTO = new UserDTO(
                "teste update service",
                "+5511999999999",
                "teste@gmail.com",
                UserType.OPERATOR,
                LocalDate.now()
        );

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.updateUser(userId, userDTO);
        assertFalse(result.isPresent());
    }
}
