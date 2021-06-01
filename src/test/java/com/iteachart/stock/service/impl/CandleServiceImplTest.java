package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Candle;
import com.iteachart.stock.entity.Company;
import com.iteachart.stock.repository.CandleRepository;
import com.iteachart.stock.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CandleServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CandleRepository candleRepository;
    @InjectMocks
    private CandleServiceImpl candleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllInPeriod(){
        Company company = new Company();
        company.setTicker("AAPL");
        Candle candle = new Candle();
        candle.setCandleCompany(company);

        List<Candle> expected = new ArrayList<>();
        expected.add(candle);

        LocalDate minusDay = LocalDate.now().minusDays(1L);
        LocalDate now = LocalDate.now();

        Mockito.when(companyRepository.findByTicker("AAPL")).thenReturn(company);
        Mockito.when(candleRepository.findAllByCandleCompanyAndDatetimeBetween(company, minusDay, now)).thenReturn(expected);

        List<Candle> actual = candleService.findAllInPeriod("AAPL", minusDay, now);

        assertEquals(expected, actual);
    }
}