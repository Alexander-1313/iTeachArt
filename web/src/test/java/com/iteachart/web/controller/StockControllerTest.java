package com.iteachart.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(basePackages = {"com.iteachart.model", "com.iteachart.load", "com.iteachart.web"})
public class StockControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetCompanyData() throws Exception{
        mvc.perform(get("/company").param("symbol", "AAPL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetAllCompanies() throws Exception{
        mvc.perform(get("/all").param("exchange", "US").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetFinancialReports() throws Exception{
        mvc.perform(get("/financialReports").param("symbol", "AAPL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetShares() throws Exception{
        mvc.perform(get("/shares").param("symbol", "AAPL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetNews() throws Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("symbol", "AAPL");
        params.add("from", LocalDate.now().minusDays(1).toString());
        params.add("to", LocalDate.now().toString());
        mvc.perform(get("/news").params(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void testAutoToUserControllerToGetCandles() throws Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("symbol", "AAPL");
        params.add("from", LocalDate.now().minusDays(1).toString());
        params.add("to", LocalDate.now().toString());
        mvc.perform(get("/candles").params(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}