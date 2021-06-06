package com.itechart.load.service.impl;

import com.itechart.load.entity.Candle;
import com.itechart.load.entity.Company;
import com.itechart.load.repository.CandleRepository;
import com.itechart.load.repository.CompanyRepository;
import com.itechart.load.service.CandleService;
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
