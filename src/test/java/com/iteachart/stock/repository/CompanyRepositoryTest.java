package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private String cik = "12345";
    private String ticker = "AAPLPEW";


    @Test
    public void testSave(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);

        Company save = companyRepository.save(company);

        assertNotNull(save);
        assertEquals(cik, save.getCik());
    }

    @Test
    public void testDeleteCompany(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);

        Company save = companyRepository.save(company);
        companyRepository.delete(save);

        boolean actual = companyRepository.existsById(save.getTicker());

        assertFalse(actual);
    }
}