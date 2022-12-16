package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.ChangePasswordDto;
import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.service.db1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/change-password/")
@Slf4j
public class ChangePasswordRestControllerV1 {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ChangePasswordRestControllerV1(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = new BCryptPasswordEncoder();;
    }

    @PostMapping("new-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto requestDto){
        try {
            Map<Object, Object> response = new HashMap<>();
            int warn = 0;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);

            String currPassword = requestDto.getCurrPassword();
            String newPassword = "{bcrypt}"+passwordEncoder.encode(requestDto.getNewPassword());

            if (user == null) {
                response.put("warn-username", "Username: " + username + " is invalid"); warn += 1;
            }
            if (!passwordEncoder.matches(currPassword, user.getPassword().substring(8))) {
                response.put("warn-password", "Password: Invalid password"); warn += 1;
            }

            if(warn >= 1) return ResponseEntity.badRequest().body(response);

            user.setPassword(newPassword);
            userService.save(user);

            response.put("username", user.getUsername());
            response.put("newPassword", user.getPassword());

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid change password");
        }
    }
}
