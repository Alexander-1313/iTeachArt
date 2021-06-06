package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.FinancialReport;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.repository.FinancialReportRepository;
import com.itechart.stock.service.FinancialReportService;
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
