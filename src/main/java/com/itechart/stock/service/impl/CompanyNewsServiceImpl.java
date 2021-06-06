package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyNews;
import com.itechart.stock.repository.CompanyNewsRepository;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.service.CompanyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyNewsServiceImpl implements CompanyNewsService {

    private final CompanyNewsRepository companyNewsRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyNews> findAllInPeriod(String symbol, LocalDate from, LocalDate to) {
        Company company = companyRepository.findByTicker(symbol);
        return companyNewsRepository.findAllByCompanyNewsCompanyAndDatetimeBetween(company, from, to);
    }
}
