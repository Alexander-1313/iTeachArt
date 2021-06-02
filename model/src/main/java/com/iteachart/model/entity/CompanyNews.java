package com.iteachart.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Company_News")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category")
    private String category;

    @Column(name = "datetime")
    private LocalDate datetime;

    @Column(name = "headline")
    private String headline;

    @Column(name = "source")
    private String source;

    @Column(name = "summary", length = 1000)
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    @ToString.Exclude
    private Company companyNewsCompany;


}
