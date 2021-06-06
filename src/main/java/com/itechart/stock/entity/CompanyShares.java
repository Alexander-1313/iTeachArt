package com.itechart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    @ToString.Exclude
    private Company companySharesCompany;
}
