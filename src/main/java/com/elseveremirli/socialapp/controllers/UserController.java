package com.elseveremirli.socialapp.controllers;

import com.elseveremirli.socialapp.dto.request.UserRequest;
import com.elseveremirli.socialapp.dto.response.UserResponse;
import com.elseveremirli.socialapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{username}")
    private UserResponse getOneUserByUsername(@PathVariable String username) {
        return userService.getOneUserByUsername(username);
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{username}")
    public UserResponse updateUser(@PathVariable String username, @RequestBody UserRequest userRequest) {
        return userService.updateUserByUsername(username,userRequest);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        if (userService.deleteUserByUsername(username)){
            return ResponseEntity.ok("User deleted successfully");
        }else {
            return ResponseEntity.badRequest().body("User could not be deleted");
        }

    }

}
