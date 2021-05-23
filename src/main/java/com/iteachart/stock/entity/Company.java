package com.iteachart.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @Column(name = "id")
    private String ticker;

    @Column(name = "country")
    private String country;

    @Column(name = "currency")
    private String currency;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "weburl")
    private String weburl;

    @Column(name = "cik")
    private String cik;

    @OneToMany(mappedBy = "companyNewsCompany")
    @JsonIgnore
    private List<CompanyNews> companyNews = new ArrayList<>();

    @OneToMany(mappedBy = "financialReportCompany")
    @JsonIgnore
    private List<FinancialReport> financialReports = new ArrayList<>();

    @OneToMany(mappedBy = "companySharesCompany")
    @JsonIgnore
    private List<CompanyShares> companyShares = new ArrayList<>();

    @OneToMany(mappedBy = "candleCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Candle> candles = new ArrayList<>();

}
