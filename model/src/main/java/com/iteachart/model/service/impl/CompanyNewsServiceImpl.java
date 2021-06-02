package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import com.iteachart.model.repository.CompanyNewsRepository;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.service.CompanyNewsService;
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
