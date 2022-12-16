package com.pzh.code.archyonix.service.impl.db1;

import com.pzh.code.archyonix.repository.db1.ShopRepository;
import com.pzh.code.archyonix.service.db1.ShopService;
import com.pzh.code.archyonix.shop.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
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
    public List<Shop> findAllByServer(String server) {
        List<Shop> result = shopRepository.findAllByServer(server);
        log.info("IN findByServer - shop: {} found by server: {}", result, server);
        return result;
    }

    @Override
    public Shop findById(Long id) {
        Shop result = shopRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no shop found by id: {}", id);
            return null;
        }

        log.info("IN findById - shop: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
        log.info("IN delete - shop with id: {} successfully deleted");
    }
}
