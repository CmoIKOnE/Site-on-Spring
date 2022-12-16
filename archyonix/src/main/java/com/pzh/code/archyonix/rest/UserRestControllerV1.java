package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.LuckPermsDto;
import com.pzh.code.archyonix.dto.ShopDto;
import com.pzh.code.archyonix.dto.UserDto;
import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.service.db2.LuckPermsMGService;
import com.pzh.code.archyonix.service.db1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final LuckPermsMGService luckPermsMGService;

    @Autowired
    public UserRestControllerV1(UserService userService, LuckPermsMGService luckPermsMGService) {
        this.userService = userService;
        this.luckPermsMGService = luckPermsMGService;
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

    /*@GetMapping(value = "luckperms")
    public ResponseEntity<List<LuckPermsDto>> getLuckPerms(){
        List<LuckPermsMG> luckPermsMG = luckPermsMGService.getAllLuckPerms();

        if(luckPermsMG == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<LuckPermsDto> result = luckPermsMG.stream().map(luckPermsMG1 -> LuckPermsDto.fromLuckPerms(luckPermsMG1)).collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/
}
