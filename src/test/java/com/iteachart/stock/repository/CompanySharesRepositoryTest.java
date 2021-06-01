package com.iteachart.stock.repository;

import com.iteachart.stock.entity.CompanyShares;
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
public class CompanySharesRepositoryTest {

    @Autowired
    private CompanySharesRepository companySharesRepository;
    private CompanyShares companyShares;

    @Before
    public void init(){
        companyShares = new CompanyShares();
        companyShares.setExchange("exchange");
        companyShares = companySharesRepository.save(companyShares);
    }

    @After
    public void destroy(){
        companySharesRepository.deleteAll();
    }

    @Test
    public void testDelete(){
        companySharesRepository.delete(companyShares);

        assertFalse(companySharesRepository.existsById(companyShares.getId()));
    }

    @Test
    public void testSave(){
        CompanyShares companySharesToSave = new CompanyShares();

        CompanyShares save = companySharesRepository.save(companySharesToSave);

        assertNotNull(save);
    }

}