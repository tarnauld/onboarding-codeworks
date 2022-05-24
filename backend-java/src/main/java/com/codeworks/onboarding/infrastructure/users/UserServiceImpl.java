package com.codeworks.onboarding.infrastructure.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findUserBy(long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUserByName(String name) {
        return this.userRepository.findByName(name);
    }
}
