package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Candle;
import com.iteachart.stock.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandleRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CandleRepository candleRepository;

    private String cik = "12345";
    private String ticker = "AAPL";

    @Test
    public void testDeleteCompanyWithCandle(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);


        Candle candle = new Candle();
        candle.setCandleCompany(company);

        List<Candle> candles = company.getCandles();
        candles.add(candle);
        company.setCandles(candles);

        Candle saveCandle = candleRepository.save(candle);
        Company saveCompany = companyRepository.save(company);

        companyRepository.delete(company);

        boolean actual = candleRepository.existsById(saveCandle.getId());
        assertFalse(actual);
    }
}