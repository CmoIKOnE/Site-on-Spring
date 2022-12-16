package com.pzh.code.archyonix.service.db1;

import com.pzh.code.archyonix.model.db1.User;

import java.util.List;
public interface UserService {

    User register(User user);

    User save(User user);

    List<User> getAll();

    User findByUsername(String username);
    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
