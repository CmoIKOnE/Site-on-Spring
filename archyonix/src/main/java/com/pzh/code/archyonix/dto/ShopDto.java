package com.pzh.code.archyonix.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pzh.code.archyonix.shop.Shop;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopDto {

    private Long id;
    private String name;
    private String special_name;
    private Integer priority;
    private String server;
    private String description;
    private Integer price;
    private String img;

    public Shop toShop(){
        Shop shop = new Shop();
        shop.setId(id);
        shop.setName(name);
        shop.setSpecial_name(special_name);
        shop.setPriority(priority);
        shop.setServer(server);
        shop.setDescription(description);
        shop.setPrice(price);
        shop.setImg(img);

        return shop;
    }

    public static ShopDto fromShop(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(shop.getId());
        shopDto.setName(shop.getName());
        shopDto.setSpecial_name(shop.getSpecial_name());
        shopDto.setPriority(shop.getPriority());
        shopDto.setServer(shop.getServer());
        shopDto.setDescription(shop.getDescription());
        shopDto.setPrice(shop.getPrice());
        shopDto.setImg(shop.getImg());

        return shopDto;
    }
}
