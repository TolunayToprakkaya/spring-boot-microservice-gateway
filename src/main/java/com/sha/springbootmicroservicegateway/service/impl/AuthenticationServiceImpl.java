package com.sha.springbootmicroservicegateway.service.impl;

import com.sha.springbootmicroservicegateway.model.Users;
import com.sha.springbootmicroservicegateway.security.UserPrincipal;
import com.sha.springbootmicroservicegateway.security.jwt.IJwtProvider;
import com.sha.springbootmicroservicegateway.service.IAuthenticationService;
import com.sha.springbootmicroservicegateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;

    @Autowired
    private IUserService userService;

    @Override
    public String signInAndReturnJWT(Users users) {
        if (!this.validateUser(users)) {
            throw new RuntimeException("Wrong Username or Password");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return jwtProvider.generateToken(userPrincipal);
    }

    private boolean validateUser(Users users) {
        return userService.validateUser(users);
    }
}
