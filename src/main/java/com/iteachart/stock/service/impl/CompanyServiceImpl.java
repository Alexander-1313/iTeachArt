package com.iteachart.stock.service.impl;

import com.iteachart.stock.dto.CompanyDto;
import com.iteachart.stock.entity.Company;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.service.CompanyService;
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
}
