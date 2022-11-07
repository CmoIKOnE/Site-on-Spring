package com.pzh.code.archyonix.service;

import com.pzh.code.archyonix.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);
    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
