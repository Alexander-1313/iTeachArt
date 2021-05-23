package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyNews;
import com.iteachart.stock.repository.CompanyNewsRepository;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.service.CompanyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
