package com.pzh.code.archyonix.service.db2;

import com.pzh.code.archyonix.model.db1.User;
import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LuckPermsMGService {
    List<LuckPermsMG> getAllLuckPerms();
    LuckPermsMG findByUsername(String username);
    Integer getPriority4MG(String username);
    void update(String username, String primary_group);
}
