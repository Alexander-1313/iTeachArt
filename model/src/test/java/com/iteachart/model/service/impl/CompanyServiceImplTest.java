package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Company;
import com.iteachart.model.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindBySymbol(){
        Company expected = new Company();
        expected.setCik("12345");
        Mockito.when(companyRepository.findByTicker("AAPL")).thenReturn(expected);

        Company actual = companyService.findBySymbol("AAPL");

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllCompanies(){
        List<Company> expected = new ArrayList<>();
        Company company = new Company();
        company.setCik("12345");
        expected.add(company);

        Mockito.when(companyRepository.findAll()).thenReturn(expected);

        List<Company> actual = companyService.findAll();

        assertEquals(expected, actual);
    }
}