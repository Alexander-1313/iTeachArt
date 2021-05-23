package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.FinancialReport;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.repository.FinancialReportRepository;
import com.iteachart.stock.service.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialReportServiceImpl implements FinancialReportService {

    private final FinancialReportRepository financialReportRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<FinancialReport> findAllBySymbol(String symbol) {
        Company company = companyRepository.findByTicker(symbol);
        return financialReportRepository.findAllByFinancialReportCompany(company);
    }
}
