package com.pzh.code.archyonix.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "rub")
    private Integer rub = 0;

    @Column(name = "onix")
    private Integer onix = 0;

    //@Column(name = "activeCode")
    //private String activeCode;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private Vote votes;


}
