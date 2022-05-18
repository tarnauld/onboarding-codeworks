package com.codeworks.onboarding.use_cases.user;

import com.codeworks.onboarding.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FindUserByName {
    @GetMapping("/users")
    public User findUserByName() {
        return new User("Tim", LocalDate.now());
    }
}
