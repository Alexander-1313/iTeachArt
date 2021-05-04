package com.iteachrt.iteachart.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Subscribe")
@Data
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "type")
    private String type;

    @Column(name = "cost")
    private BigDecimal cost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_subscriber", referencedColumnName = "id")
    private User subscribeUser;

}
