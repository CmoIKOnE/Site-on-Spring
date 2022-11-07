package com.pzh.code.archyonix.service;

import com.pzh.code.archyonix.shop.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getAll();

    Shop findByCategory(String category);

    void delete(Long id);
}
