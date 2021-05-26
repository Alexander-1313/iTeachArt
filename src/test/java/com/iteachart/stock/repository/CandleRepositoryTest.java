package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Candle;
import com.iteachart.stock.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandleRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CandleRepository candleRepository;

    private String cik = "123456";
    private String ticker = "AAPL6";

    @Test
    public void testDeleteCompanyWithCandle(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);

        Candle candle = new Candle();
        candle.setVolumeData(44L);
        candle.setCandleCompany(company);

        List<Candle> candles = company.getCandles();
        candles.add(candle);
        company.setCandles(candles);

        Company saveCompany = companyRepository.save(company);

        assertTrue(candleRepository.findAll().stream().anyMatch(c -> c.getVolumeData() == 44L));

        companyRepository.delete(saveCompany);

        assertFalse(candleRepository.findAll().stream().anyMatch(c -> c.getVolumeData() == 44L));
    }
}