package com.iteachart.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @Column(name = "id", nullable = false, unique = true)
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

    @OneToMany(mappedBy = "companyNewsCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CompanyNews> companyNews = new ArrayList<>();

    @OneToMany(mappedBy = "financialReportCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FinancialReport> financialReports = new ArrayList<>();

    @OneToMany(mappedBy = "companySharesCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CompanyShares> companyShares = new ArrayList<>();

    @OneToMany(mappedBy = "candleCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Candle> candles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companies")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

}
