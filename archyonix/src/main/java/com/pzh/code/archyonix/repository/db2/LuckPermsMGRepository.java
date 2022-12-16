package com.pzh.code.archyonix.repository.db2;

import com.pzh.code.archyonix.model.db2.LuckPermsMG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuckPermsMGRepository extends JpaRepository<LuckPermsMG, String> {
    List<LuckPermsMG> findAll();
    LuckPermsMG findByUsername(String username);
}
