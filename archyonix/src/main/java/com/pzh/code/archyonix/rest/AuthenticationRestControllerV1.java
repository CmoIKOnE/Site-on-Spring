package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.AuthenticationRequestDto;
import com.pzh.code.archyonix.dto.RegisterRequestDto;
import com.pzh.code.archyonix.model.User;
import com.pzh.code.archyonix.repository.UserRepository;
import com.pzh.code.archyonix.security.jwt.JwtTokenProvider;
import com.pzh.code.archyonix.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@Slf4j
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto requestDto){
        try {
            Map<Object, Object> response = new HashMap<>();
            int warn = 0;

            String username = requestDto.getUsername();
            String email = requestDto.getEmail();
            String password = requestDto.getPassword();

            User user = userService.findByUsername(username);
            User userEmail = userService.findByEmail(email);

            if (user != null) {
                response.put("warn-username", "Username: " + username + " is not free"); warn += 1;
            }
            if (userEmail != null) {
                response.put("warn-email", "Email: " + email + " is not free"); warn += 1;
            }

            if(warn >= 1) return ResponseEntity.badRequest().body(response);

            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            userService.register(user);

            response.put("username", user.getUsername());
            response.put("email", user.getEmail());

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid register");
        }
    }
}
