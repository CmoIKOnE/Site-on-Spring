package com.pzh.code.archyonix.model.db1;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_votes")
@Data
public class Vote {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "votes")
    private Integer votes;


}
