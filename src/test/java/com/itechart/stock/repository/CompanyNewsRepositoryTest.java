package com.itechart.stock.repository;

import com.itechart.stock.entity.CompanyNews;
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
public class CompanyNewsRepositoryTest {

    @Autowired
    private CompanyNewsRepository companyNewsRepository;
    private CompanyNews companyNews;

    @Before
    public void init(){
        companyNews = new CompanyNews();
        companyNews.setHeadline("headline");
        companyNews = companyNewsRepository.save(companyNews);
    }

    @After
    public void destroy(){
        companyNewsRepository.deleteAll();
    }

    @Test
    public void testDelete(){
        companyNewsRepository.delete(companyNews);

        assertFalse(companyNewsRepository.existsById(companyNews.getId()));
    }

    @Test
    public void testSave(){
        CompanyNews companyNewsToSave = new CompanyNews();

        CompanyNews save = companyNewsRepository.save(companyNewsToSave);

        assertNotNull(save);
    }

}