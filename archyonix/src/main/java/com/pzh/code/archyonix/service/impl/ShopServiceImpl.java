package com.pzh.code.archyonix.service.impl;

import com.pzh.code.archyonix.repository.ShopRepository;
import com.pzh.code.archyonix.service.ShopService;
import com.pzh.code.archyonix.shop.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Shop> getAll() {
        List<Shop> result = shopRepository.findAll();
        log.info("IN getAll - {} shops found", result.size());
        return result;
    }

    @Override
    public Shop findByCategory(String category) {
        Shop result = shopRepository.findByCategory(category);
        log.info("IN findByCategory - shop: {} found by category: {}", result, category);
        return result;
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
        log.info("IN delete - shop with id: {} successfully deleted");
    }
}
