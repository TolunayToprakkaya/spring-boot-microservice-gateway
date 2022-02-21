package com.sha.springbootmicroservicegateway.security;

import com.sha.springbootmicroservicegateway.model.Users;
import com.sha.springbootmicroservicegateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with username:" + username);
        }

        return new UserPrincipal(users.getId(), users.getUsername(), users.getPassword());
    }
}
