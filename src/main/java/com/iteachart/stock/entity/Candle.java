package com.iteachart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Candle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "open_price")
    private BigDecimal openPrice;

    @Column(name = "close_price")
    private BigDecimal closePrice;

    @Column(name = "low_price")
    private BigDecimal lowPrice;

    @Column(name = "high_price")
    private BigDecimal highPrice;

    @Column(name = "datetime")
    private Date datetime;

    @Column(name = "volume_data")
    private Long volumeData;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    private Company candleCompany;
}
