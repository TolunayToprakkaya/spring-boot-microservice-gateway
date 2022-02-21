package com.sha.springbootmicroservicegateway.controller;

import com.sha.springbootmicroservicegateway.model.Users;
import com.sha.springbootmicroservicegateway.service.IAuthenticationService;
import com.sha.springbootmicroservicegateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Users users) {
        if (!ObjectUtils.isEmpty(userService.findByUsername(users.getUsername()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody Users users) {

        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(users), HttpStatus.OK);
    }
}
