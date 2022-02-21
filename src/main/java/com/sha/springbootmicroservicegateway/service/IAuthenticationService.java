package com.sha.springbootmicroservicegateway.service;

import com.sha.springbootmicroservicegateway.model.Users;

public interface IAuthenticationService {

    String signInAndReturnJWT(Users users);
}
