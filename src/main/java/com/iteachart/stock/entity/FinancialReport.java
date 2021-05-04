package com.iteachrt.iteachart.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Financial_Report")
@Data
public class FinancialReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    private Company financialReportCompany;
}
