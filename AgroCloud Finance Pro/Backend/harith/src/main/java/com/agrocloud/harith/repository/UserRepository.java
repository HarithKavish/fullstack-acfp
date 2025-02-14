package com.agrocloud.harith.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrocloud.harith.model.User;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String username);

}