package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import com.iteachart.model.repository.CandleRepository;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.service.CandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandleServiceImpl implements CandleService {

    private final CompanyRepository companyRepository;
    private final CandleRepository candleRepository;

    @Override
    public List<Candle> findAllInPeriod(String symbol, LocalDate from, LocalDate to) {
        Company company = companyRepository.findByTicker(symbol);
        return candleRepository.findAllByCandleCompanyAndDatetimeBetween(company, from, to);
    }
}
