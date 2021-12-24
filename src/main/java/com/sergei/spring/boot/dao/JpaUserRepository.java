package com.sergei.spring.boot.dao;

import com.sergei.spring.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles where u.email = :email")
    User queryUsersByEmail(@Param("email") String email);
}
