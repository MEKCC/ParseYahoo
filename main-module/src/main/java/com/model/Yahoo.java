package com.model;


import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@Table(name = "yahoo")
public class Yahoo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String symbol;
    private String name;
    private String exch;
    private String type;
    private String exchDisp;
    private String typeDisp;
}
