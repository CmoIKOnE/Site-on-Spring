package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.UserDto;
import com.pzh.code.archyonix.model.User;
import com.pzh.code.archyonix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}