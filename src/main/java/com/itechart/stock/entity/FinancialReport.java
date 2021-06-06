package com.itechart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Financial_Report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "cik")
    private String cik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    @ToString.Exclude
    private Company financialReportCompany;
}
