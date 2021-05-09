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

    private String cik = "12345";
    private String ticker = "AAPL";

    @Test
    public void testDeleteCompanyWithCompanyNews(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);


        CompanyShares companyShares = new CompanyShares();
        companyShares.setCompanySharesCompany(company);

        List<CompanyShares> companySharesList = company.getCompanyShares();
        companySharesList.add(companyShares);
        company.setCompanyShares(companySharesList);

        CompanyShares saveCompanyShares = companySharesRepository.save(companyShares);
        Company saveCompany = companyRepository.save(company);

        companyRepository.delete(company);

        boolean actual = companySharesRepository.existsById(saveCompanyShares.getId());
        assertFalse(actual);
    }

}