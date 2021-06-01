package com.iteachart.stock.repository;

import com.iteachart.stock.entity.FinancialReport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialReportRepositoryTest {

    @Autowired
    private FinancialReportRepository financialReportRepository;
    private FinancialReport financialReport;

    @Before
    public void init(){
        financialReport = new FinancialReport();
        financialReport.setCik("cik");
        financialReport = financialReportRepository.save(financialReport);
    }

    @After
    public void destroy(){
        financialReportRepository.deleteAll();
    }

    @Test
    public void testDelete(){
        financialReportRepository.delete(financialReport);

        assertFalse(financialReportRepository.existsById(financialReport.getId()));
    }

    @Test
    public void testSave(){
        FinancialReport companySharesToSave = new FinancialReport();

        FinancialReport save = financialReportRepository.save(companySharesToSave);

        assertNotNull(save);
    }

}