package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyNews;
import com.itechart.stock.repository.CompanyNewsRepository;
import com.itechart.stock.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyNewsServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyNewsRepository companyNewsRepository;
    @InjectMocks
    private CompanyNewsServiceImpl companyNewsService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllInPeriod(){
        Company company = new Company();
        company.setTicker("AAPL");
        CompanyNews companyNews = new CompanyNews();
        companyNews.setHeadline("headline");

        List<CompanyNews> expected = new ArrayList<>();
        expected.add(companyNews);

        LocalDate minusDay = LocalDate.now().minusDays(1L);
        LocalDate now = LocalDate.now();

        Mockito.when(companyRepository.findByTicker("AAPL")).thenReturn(company);
        Mockito.when(companyNewsRepository.findAllByCompanyNewsCompanyAndDatetimeBetween(company, minusDay, now)).thenReturn(expected);

        List<CompanyNews> actual = companyNewsService.findAllInPeriod("AAPL", minusDay, now);

        assertEquals(expected, actual);
    }
}