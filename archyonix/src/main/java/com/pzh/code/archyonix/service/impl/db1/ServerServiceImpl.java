package com.pzh.code.archyonix.service.impl.db1;

import com.pzh.code.archyonix.model.db1.Server;
import com.pzh.code.archyonix.repository.db1.ServerRepository;
import com.pzh.code.archyonix.service.db1.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server findByServer(String server) {
        Server result = serverRepository.findByName(server);
        log.info("IN findByServer - server: {} found by server: {}", result, server);
        return result;
    }

}
