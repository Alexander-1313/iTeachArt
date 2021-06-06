package com.itechart.load.service.impl;

import com.itechart.load.entity.Company;
import com.itechart.load.entity.CompanyShares;
import com.itechart.load.repository.CompanyRepository;
import com.itechart.load.repository.CompanySharesRepository;
import com.itechart.load.service.SharesService;
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
