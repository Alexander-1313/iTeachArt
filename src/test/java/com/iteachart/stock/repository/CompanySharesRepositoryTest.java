package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyNews;
import com.iteachart.stock.entity.CompanyShares;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanySharesRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanySharesRepository companySharesRepository;

    private String cik = "123454";
    private String ticker = "AAPL4";

    @Test
    public void testDeleteCompanyWithCompanyNews(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);


        CompanyShares companyShares = new CompanyShares();
        companyShares.setCompanySharesCompany(company);
        companyShares.setExchange("BL");

        List<CompanyShares> companySharesList = company.getCompanyShares();
        companySharesList.add(companyShares);
        company.setCompanyShares(companySharesList);

        Company saveCompany = companyRepository.save(company);

        assertTrue(companySharesRepository.findAll().stream().anyMatch(c->c.getExchange().equals("BL")));

        companyRepository.delete(saveCompany);

        assertFalse(companySharesRepository.findAll().stream().anyMatch(c->c.getExchange().equals("BL")));
    }

}