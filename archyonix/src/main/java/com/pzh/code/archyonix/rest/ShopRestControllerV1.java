package com.pzh.code.archyonix.rest;

import com.pzh.code.archyonix.dto.ShopDto;
import com.pzh.code.archyonix.service.ShopService;
import com.pzh.code.archyonix.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/shop/")
public class ShopRestControllerV1 {

    private final ShopService shopService;

    @Autowired
    public ShopRestControllerV1(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(value = "{category}")
    public ResponseEntity<ShopDto> getShopByCategory(@PathVariable(name = "category") String category){
        Shop shop = shopService.findByCategory(category);

        if(shop == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ShopDto result = ShopDto.fromShop(shop);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
