package com.yolo.backend.controllers;


import com.yolo.backend.domain.User;
import com.yolo.backend.domain.enums.UserType;
import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "For user management")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Operation(summary = "It gets all users.", description = "It returns a list containing every existing user in the system.")
    @ApiResponse(responseCode = "200", description = "Users' list has been loaded successfully.",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = User.class)))
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "It gets a user by id.", description = "It returns every information about one specific user.")
    @ApiResponse(responseCode = "200", description = "User's information has been loaded successfully.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Long id) {
        Optional<User> userModel = userRepository.findById(id);

        if(userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModel.get());

    }

    @PostMapping
    @Operation(summary = "It saves a new user.", description = "It saves a new user in the system.")
    @ApiResponse(responseCode = "201", description = "User's information has been saved successfully.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userDTO) {
        var userModel = new User();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @PutMapping("/{id}")
    @Operation(summary = "It updates a user.", description = "It updates the information about an existing user in the system.")
    @ApiResponse(responseCode = "200", description = "User's information has been updated successfully.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid UserDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = user.get();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "It deletes a user.", description = "It deletes an existing user in the system.")
    @ApiResponse(responseCode = "200", description = "User has been deleted successfully.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @GetMapping("/filter")
    @Operation(summary = "It filters by user type.", description = "It returns a filtered list of users.")
    @ApiResponse(responseCode = "200", description = "Filtered list has been returned. Valid values: Operador," +
            " Fornecedor, Hóspede and Proprietário.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<Object> filterUserByUserType(@RequestParam String userType) {
        try {
            UserType type = UserType.fromString(userType);
            List<User> userList = userRepository.findByUserType(type);
            if(userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user was found for this user type.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user type: " + userType);
        }

    }

}
