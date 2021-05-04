package com.iteachrt.iteachart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Company_News")
@Data
public class CompanyNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category")
    private String category;

    @Column(name = "datetime")
    private Date datetime;

    @Column(name = "headline")
    private String headline;

    @Column(name = "source")
    private String source;

    @Column(name = "summary")
    private String summary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_ticker", referencedColumnName = "id")
    private Company companyNewsCompany;


}
