package com.pzh.code.archyonix.repository.db1;

import com.pzh.code.archyonix.model.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String name);
    //Boolean existsByEmail(String email);
}
