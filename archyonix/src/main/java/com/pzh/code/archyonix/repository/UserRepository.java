package com.pzh.code.archyonix.repository;

import com.pzh.code.archyonix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String name);
    //Boolean existsByEmail(String email);
}
