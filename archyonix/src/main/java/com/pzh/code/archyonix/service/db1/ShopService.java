package com.pzh.code.archyonix.service.db1;

import com.pzh.code.archyonix.shop.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getAll();

    List<Shop> findAllByServer(String server);

    Shop findById(Long id);

    void delete(Long id);
}
