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

    @Column(name = "special_name")
    private String special_name;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "server")
    private String server;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "img")
    private String img;
}
