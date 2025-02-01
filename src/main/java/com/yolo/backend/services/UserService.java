package com.yolo.backend.services;

import com.yolo.backend.domain.User;
import com.yolo.backend.domain.enums.UserType;
import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(UserDTO userDTO) {
        var userModel = new User();
        BeanUtils.copyProperties(userDTO, userModel);
        return userRepository.save(userModel);
    }

    public Optional<User> updateUser(Long id, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            var userModel = user.get();
            BeanUtils.copyProperties(userDTO, userModel);
            return Optional.of(userRepository.save(userModel));
        }
        return Optional.empty();
    }

    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> filterUserByUserType(String userType) {
        try {
            UserType type = UserType.fromString(userType);
            return userRepository.findByUserType(type);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }
}
