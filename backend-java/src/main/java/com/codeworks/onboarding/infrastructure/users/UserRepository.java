package com.codeworks.onboarding.infrastructure.users;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findById(long id);
    List<UserEntity> findAll();
    UserEntity deleteById(long id);

    UserEntity findByName(String name);
}
