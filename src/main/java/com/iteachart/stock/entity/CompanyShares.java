package com.iteachart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Company_Shares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyShares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "market_capitaliz")
    private Integer marketCapitalization;

    @Column(name = "share_outstanding")
    private Integer shareOutstanding;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    private Company companySharesCompany;
}
