package com.iteachart.model.repository;

import com.iteachart.model.entity.Candle;
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
public class CandleRepositoryTest {

    @Autowired
    private CandleRepository candleRepository;
    private Candle candle;

    @Before
    public void init(){
        candle = new Candle();
        candle.setVolumeData(33L);
        candle = candleRepository.save(candle);
    }

    @After
    public void destroy(){
        candleRepository.deleteAll();
    }

    @Test
    public void testDelete(){
        candleRepository.delete(candle);

        assertFalse(candleRepository.existsById(candle.getId()));

    }

    @Test
    public void testSave(){
        Candle candle = new Candle();
        candle.setVolumeData(44L);

        Candle save = candleRepository.save(candle);

        assertNotNull(save);
    }
}