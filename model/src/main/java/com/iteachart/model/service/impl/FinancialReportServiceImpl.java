package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.FinancialReport;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.repository.FinancialReportRepository;
import com.iteachart.model.service.FinancialReportService;
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
