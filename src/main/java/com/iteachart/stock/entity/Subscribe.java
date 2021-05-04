package com.iteachart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Subscribe")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
