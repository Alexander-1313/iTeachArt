package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Company;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findBySymbol(String symbol) {
        return companyRepository.findByTicker(symbol);
    }

    @Override
    public List<Company> findAllByCountry(String country) {
        return companyRepository.findAllByCountry(country);
    }
}
