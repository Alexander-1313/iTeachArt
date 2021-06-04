package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyShares;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.repository.CompanySharesRepository;
import com.iteachart.model.service.SharesService;
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
