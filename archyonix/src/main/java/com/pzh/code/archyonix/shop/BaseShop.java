package com.pzh.code.archyonix.shop;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "img")
    private String img;
}
