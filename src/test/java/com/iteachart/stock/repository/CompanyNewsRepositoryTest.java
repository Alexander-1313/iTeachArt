package com.iteachart.stock.repository;

import com.iteachart.stock.entity.CompanyNews;
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