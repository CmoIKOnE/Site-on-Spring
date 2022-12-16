package com.pzh.code.archyonix.service.impl.db2;

import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import com.pzh.code.archyonix.repository.db2.LuckPermsMGRepository;
import com.pzh.code.archyonix.service.db2.LuckPermsMGService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LuckPermsMGServiceImpl implements LuckPermsMGService {
    private final LuckPermsMGRepository luckPermsMGRepository;

    @Autowired
    public LuckPermsMGServiceImpl(LuckPermsMGRepository luckPermsMGRepository) {
        this.luckPermsMGRepository = luckPermsMGRepository;
    }

    @Override
    public List<LuckPermsMG> getAllLuckPerms() {
        List<LuckPermsMG> result = luckPermsMGRepository.findAll();
        log.info("IN getAllLuckperms - {}", result);
        return result;
    }

    @Override
    public LuckPermsMG findByUsername(String username) {
        LuckPermsMG result = luckPermsMGRepository.findByUsername(username);
        log.info("IN findByUsername - username: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public void update(String username, String primary_group) {
        LuckPermsMG lp = luckPermsMGRepository.findByUsername(username.toLowerCase());
        lp.setPrimary_group(primary_group);
        luckPermsMGRepository.save(lp);
        log.info("IN LuckPermsMG.update() - {} -> {}", username, primary_group);
    }
    @Override
    public Integer getPriority4MG(String username) {
        LuckPermsMG lp = luckPermsMGRepository.findByUsername(username.toLowerCase());
        return switch (lp.getPrimary_group()) {
            case "vip" -> 1;
            case "vip_plus" -> 2;
            default -> 0;
        };
    }
}
