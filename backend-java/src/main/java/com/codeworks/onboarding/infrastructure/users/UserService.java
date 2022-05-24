package com.codeworks.onboarding.infrastructure.users;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();
    UserEntity findUserBy(long id);
    UserEntity create(UserEntity user);
    UserEntity deleteUser(long id);

    UserEntity findUserByName(String name);
}
