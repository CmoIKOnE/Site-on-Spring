package com.pzh.code.archyonix.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
@Data
public class Shop extends BaseShop {
}
