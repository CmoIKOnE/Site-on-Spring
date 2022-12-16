package com.pzh.code.archyonix.repository.db1;

import com.pzh.code.archyonix.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByServer(String server);
}
