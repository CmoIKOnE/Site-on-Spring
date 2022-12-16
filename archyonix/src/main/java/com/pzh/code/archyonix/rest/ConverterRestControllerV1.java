package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.ConverterDto;
import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.service.db1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping(value = "/api/v1/convert/")
@Slf4j
public class ConverterRestControllerV1 {

    private final UserService userService;

    public ConverterRestControllerV1(UserService userService) {
        this.userService = userService;
    }
    @PutMapping("rubToOnix")
    public ResponseEntity<?> convertRubToOnix(@RequestBody ConverterDto requestDto){
        try {
            Map<Object, Object> response = new HashMap<>();
            int warn = 0;

            Integer rub = requestDto.getRub();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);

            assert user != null;
            if (rub > user.getRub()){
                response.put("warn-rub", "Rub: " + rub + " > " + user.getRub()); warn += 1;
            }

            if(warn >= 1) return ResponseEntity.badRequest().body(response);

            Integer result = rub * 10;
            user.setOnix(user.getOnix() + result);
            user.setRub(user.getRub() - rub);
            userService.save(user);



            response.put("username", user.getUsername());
            response.put("onix", user.getOnix());
            response.put("rub", user.getRub());

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid converter");
        }
    }
}
