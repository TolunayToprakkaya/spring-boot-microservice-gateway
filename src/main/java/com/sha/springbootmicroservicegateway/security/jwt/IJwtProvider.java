package com.sha.springbootmicroservicegateway.security.jwt;

import com.sha.springbootmicroservicegateway.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IJwtProvider {
    String generateToken(UserPrincipal userPrincipal);

    Authentication getAuthentication(HttpServletRequest httpServletRequest);

    boolean isTokenValid(HttpServletRequest httpServletRequest);
}
