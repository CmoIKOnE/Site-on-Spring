package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.TranslateOnixToServerDto;
import com.pzh.code.archyonix.history.HistoryOnixToServer;
import com.pzh.code.archyonix.model.db1.Server;
import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.service.db1.HistoryOnixToServerService;
import com.pzh.code.archyonix.service.db1.ServerService;
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
@RequestMapping(value = "/api/v1/translate/")
@Slf4j
public class TranslateOnixToServerRestControllerV1 {

    private final UserService userService;
    private final ServerService serverService;
    private final HistoryOnixToServerService historyOnixToServerService;

    public TranslateOnixToServerRestControllerV1(UserService userService, ServerService serverService, HistoryOnixToServerService historyOnixToServerService) {
        this.userService = userService;
        this.serverService = serverService;
        this.historyOnixToServerService = historyOnixToServerService;
    }
    @PostMapping("OnixToServer")
    public ResponseEntity<?> translateOnixToServer(@RequestBody TranslateOnixToServerDto requestDto){
        try {
            Map<Object, Object> response = new HashMap<>();
            int warn = 0;

            Integer onix = requestDto.getOnix();
            String server = requestDto.getServer();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);
            Server findServer = serverService.findByServer(server);

            assert user != null;
            if (onix > user.getOnix()){
                response.put("warn-onix", "Onix: " + onix + " > " + user.getOnix()); warn += 1;
            }
            if (findServer == null){
                response.put("warn-server", "Server: " + server + " does not exist"); warn += 1;
            }

            if(warn >= 1) return ResponseEntity.badRequest().body(response);

            user.setOnix(user.getOnix() - onix);
            userService.save(user);

            //TODO: Сделать подключение к серверу минигейма

            HistoryOnixToServer ho2s = new HistoryOnixToServer();
            ho2s.setUsername(username);
            ho2s.setOnix(onix);
            ho2s.setServer(server);
            historyOnixToServerService.register(ho2s);

            response.put("username", user.getUsername());
            response.put("onix-user", user.getOnix());
            response.put("onix-translate", ho2s.getOnix());
            response.put("server", ho2s.getServer());

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid translate");
        }
    }
}
