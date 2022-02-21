package com.sha.springbootmicroservicegateway.service.impl;

import com.sha.springbootmicroservicegateway.model.Users;
import com.sha.springbootmicroservicegateway.repository.IUserRepository;
import com.sha.springbootmicroservicegateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setCreateTime(LocalDateTime.now());
        return userRepository.save(users);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean validateUser(Users users) {
        boolean usernameMatches = userRepository.existsByUsername(users.getUsername());
        //Get Password To DB
        boolean passwordMatches = false;
        if (usernameMatches) {
            Users encodedUserPassword = userRepository.findByUsername(users.getUsername());
            passwordMatches = passwordEncoder.matches(users.getPassword(), encodedUserPassword.getPassword());
        }

        return usernameMatches && passwordMatches;
    }
}
