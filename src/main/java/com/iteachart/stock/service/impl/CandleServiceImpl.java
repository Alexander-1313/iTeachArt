package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Candle;
import com.iteachart.stock.entity.Company;
import com.iteachart.stock.repository.CandleRepository;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.service.CandleService;
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
