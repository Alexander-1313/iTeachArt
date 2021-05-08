package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyNews;
import com.iteachart.stock.entity.FinancialReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialReportRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private FinancialReportRepository financialReportRepository;

    private String cik = "12345";
    private String ticker = "AAPL";

    @Test
    public void testDeleteCompanyWithCompanyNews(){
        Company company = new Company();
        company.setCik(cik);
        company.setTicker(ticker);


        FinancialReport financialReport = new FinancialReport();
        financialReport.setFinancialReportCompany(company);

        List<FinancialReport> companyFinancialReports = company.getFinancialReports();
        companyFinancialReports.add(financialReport);
        company.setFinancialReports(companyFinancialReports);

        FinancialReport saveFinancialReport = financialReportRepository.save(financialReport);
        Company saveCompany = companyRepository.save(company);

        companyRepository.delete(company);

        boolean actual = financialReportRepository.existsById(saveFinancialReport.getId());
        assertFalse(actual);
    }

}