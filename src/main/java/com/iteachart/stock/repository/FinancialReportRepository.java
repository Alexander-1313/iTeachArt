package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.FinancialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {

    List<FinancialReport> findAllByFinancialReportCompany(Company company);
}
