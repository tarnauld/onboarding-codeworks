package com.codeworks.onboarding.infrastructure;

import com.codeworks.onboarding.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    User findById(long id);
}
