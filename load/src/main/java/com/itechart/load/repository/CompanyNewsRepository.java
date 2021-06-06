package com.itechart.load.repository;

import com.itechart.load.entity.Company;
import com.itechart.load.entity.CompanyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompanyNewsRepository extends JpaRepository<CompanyNews, Long> {
    List<CompanyNews> findAllByCompanyNewsCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
