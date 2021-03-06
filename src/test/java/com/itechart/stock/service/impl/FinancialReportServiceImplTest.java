package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.FinancialReport;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.repository.FinancialReportRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinancialReportServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private FinancialReportRepository financialReportRepository;
    @InjectMocks
    private FinancialReportServiceImpl financialReportService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllBySymbol(){
        Company company = new Company();
        company.setTicker("AAPL");
        FinancialReport financialReport = new FinancialReport();
        financialReport.setCik("12345");

        List<FinancialReport> expected = new ArrayList<>();
        expected.add(financialReport);

        Mockito.when(companyRepository.findByTicker("AAPL")).thenReturn(company);
        Mockito.when(financialReportRepository.findAllByFinancialReportCompany(company)).thenReturn(expected);

        List<FinancialReport> actual = financialReportService.findAllBySymbol("AAPL");

        assertEquals(expected, actual);
    }
}