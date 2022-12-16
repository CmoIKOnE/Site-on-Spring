package com.pzh.code.archyonix.repository.db1;

import com.pzh.code.archyonix.model.db1.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByName(String name);
}
