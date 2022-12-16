package com.pzh.code.archyonix.repository.db1;

import com.pzh.code.archyonix.model.db1.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
