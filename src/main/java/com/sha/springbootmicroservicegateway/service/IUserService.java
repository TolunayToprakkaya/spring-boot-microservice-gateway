package com.sha.springbootmicroservicegateway.service;

import com.sha.springbootmicroservicegateway.model.Users;

import java.util.List;

public interface IUserService {

    Users saveUser(Users users);

    void deleteUser(Long userId);

    Users findByUsername(String username);

    List<Users> findAllUsers();

    boolean validateUser(Users users);
}
