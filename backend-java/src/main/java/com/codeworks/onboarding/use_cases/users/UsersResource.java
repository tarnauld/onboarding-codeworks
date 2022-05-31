package com.codeworks.onboarding.use_cases.users;

import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.codeworks.onboarding.infrastructure.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersResource {
    private final UserService userService;

    @Autowired
    public UsersResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @DeleteMapping("/users/{id}")
    public UserEntity deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
