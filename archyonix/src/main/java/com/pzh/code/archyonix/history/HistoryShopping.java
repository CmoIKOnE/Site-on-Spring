package com.pzh.code.archyonix.history;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history_shopping")
@Data
public class HistoryShopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "item")
    private String item;

    @Column(name = "rub")
    private Integer rub;

    @CreatedDate
    @Column(name = "date")
    private Date date;

    @Column(name = "reason")
    private String reason = "OK";

}
