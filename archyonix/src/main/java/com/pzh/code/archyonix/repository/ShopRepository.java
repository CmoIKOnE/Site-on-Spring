package com.pzh.code.archyonix.repository;

import com.pzh.code.archyonix.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByCategory(String category);

}
