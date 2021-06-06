package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Candle;
import com.itechart.stock.entity.Company;
import com.itechart.stock.repository.CandleRepository;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.service.CandleService;
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
