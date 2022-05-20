package com.codeworks.onboarding.use_cases.users;

import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.codeworks.onboarding.infrastructure.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersResource {
    private final UserService userService;

    @Autowired
    public UsersResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @CrossOrigin(origins = "*")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserEntity findUserBy(@PathVariable long id) {
        return userService.findUserBy(id);
    }

    @PostMapping("/users")
    @CrossOrigin(origins = "*")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @DeleteMapping("/users/{id}")
    @CrossOrigin(origins = "*")
    public UserEntity deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
