package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Candle;
import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyNews;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyNewsRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyNewsRepository companyNewsRepository;

    private String cik = "12345";
    private String ticker = "AAPL";

    @Test
    public void testDeleteCompanyWithCompanyNews(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);


        CompanyNews companyNews = new CompanyNews();
        companyNews.setCompanyNewsCompany(company);

        List<CompanyNews> companyNewsList = company.getCompanyNews();
        companyNewsList.add(companyNews);
        company.setCompanyNews(companyNewsList);

        CompanyNews saveCompanyNews = companyNewsRepository.save(companyNews);
        Company saveCompany = companyRepository.save(company);

        companyRepository.delete(company);

        boolean actual = companyNewsRepository.existsById(saveCompanyNews.getId());
        assertFalse(actual);
    }

}