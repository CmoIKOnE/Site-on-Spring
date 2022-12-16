package com.pzh.code.archyonix.history;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history_onix_to_server")
@Data
public class HistoryOnixToServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "onix")
    private Integer onix;

    @Column(name = "server")
    private String server;

    @CreatedDate
    @Column(name = "date")
    private Date date;

    @Column(name = "reason")
    private String reason = "OK";
}
