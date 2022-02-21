package com.sha.springbootmicroservicegateway.repository;

import com.sha.springbootmicroservicegateway.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select u.password from Users u where u.username = :username")
    String getPasswordByUsername(@Param("username") String username);
}
