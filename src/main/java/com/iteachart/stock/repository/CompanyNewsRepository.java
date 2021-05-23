package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CompanyNewsRepository extends JpaRepository<CompanyNews, Long> {

    List<CompanyNews> findAllByCompanyNewsCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
