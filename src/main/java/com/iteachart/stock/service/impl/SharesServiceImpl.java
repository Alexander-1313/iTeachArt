package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyShares;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.repository.CompanySharesRepository;
import com.iteachart.stock.service.SharesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharesServiceImpl implements SharesService {

    private final CompanySharesRepository companySharesRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyShares> findAllBySymbol(String symbol) {
        Company byTicker = companyRepository.findByTicker(symbol);
        return companySharesRepository.findAllByCompanySharesCompany(byTicker);
    }
}
