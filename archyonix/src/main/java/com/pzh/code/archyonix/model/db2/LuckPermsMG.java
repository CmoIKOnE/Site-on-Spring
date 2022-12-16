package com.pzh.code.archyonix.model.db2;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "luckperms", name = "luckperms_players")
public class LuckPermsMG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;
    @Column(name = "username")
    private String username;
    @Column(name = "primary_group")
    private String primary_group;

}
