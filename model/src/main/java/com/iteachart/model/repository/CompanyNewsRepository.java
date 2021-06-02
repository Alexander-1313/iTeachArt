package com.iteachart.model.repository;

import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompanyNewsRepository extends JpaRepository<CompanyNews, Long> {

    List<CompanyNews> findAllByCompanyNewsCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
