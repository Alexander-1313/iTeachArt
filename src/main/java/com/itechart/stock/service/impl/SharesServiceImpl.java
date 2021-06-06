package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyShares;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.repository.CompanySharesRepository;
import com.itechart.stock.service.SharesService;
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
