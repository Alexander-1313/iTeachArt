package com.itechart.stock.repository;

import com.itechart.stock.entity.CompanyShares;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
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