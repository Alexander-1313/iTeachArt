package com.itechart.load.service.impl;

import com.itechart.load.entity.Company;
import com.itechart.load.entity.FinancialReport;
import com.itechart.load.repository.CompanyRepository;
import com.itechart.load.repository.FinancialReportRepository;
import com.itechart.load.service.FinancialReportService;
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
