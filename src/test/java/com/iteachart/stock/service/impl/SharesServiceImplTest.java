package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyShares;
import com.iteachart.stock.entity.FinancialReport;
import com.iteachart.stock.repository.CompanyRepository;
import com.iteachart.stock.repository.CompanySharesRepository;
import com.iteachart.stock.repository.FinancialReportRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SharesServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanySharesRepository companySharesRepository;
    @InjectMocks
    private SharesServiceImpl sharesService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllBySymbol(){
        Company company = new Company();
        company.setTicker("AAPL");
        CompanyShares companyShares = new CompanyShares();
        companyShares.setMarketCapitalization(1234567);

        List<CompanyShares> expected = new ArrayList<>();
        expected.add(companyShares);

        Mockito.when(companyRepository.findByTicker("AAPL")).thenReturn(company);
        Mockito.when(companySharesRepository.findAllByCompanySharesCompany(company)).thenReturn(expected);

        List<CompanyShares> actual = sharesService.findAllBySymbol("AAPL");

        assertEquals(expected, actual);
    }
}