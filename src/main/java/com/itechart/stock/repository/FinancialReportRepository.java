package com.itechart.stock.repository;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.FinancialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {

    List<FinancialReport> findAllByFinancialReportCompany(Company company);
}
