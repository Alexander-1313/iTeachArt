package com.iteachart.stock.controller;

import com.iteachart.stock.dto.CandleDto;
import com.iteachart.stock.dto.CompanyDto;
import com.iteachart.stock.dto.FinancialReportDto;
import com.iteachart.stock.entity.*;
import com.iteachart.stock.feign.StockFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class StockController {

    @Autowired
    private StockFeignClient stockFeignClient;

    @GetMapping("/all")
    public List<CompanyDto> getAllCompanies(@RequestParam String exchange){
        return stockFeignClient.getAllCompanies(exchange);
    }

    @GetMapping("/company")
    public Company getCompany(@RequestParam String symbol){
        return stockFeignClient.getCompany(symbol);
    }

    @Scheduled(fixedRate = 10000)
    public void testScheduling(){
        System.out.println(stockFeignClient.getCompany("AAPL"));
    }

    @GetMapping("/financialReport")
    public FinancialReportDto getFinancialReport(@RequestParam String symbol){
        return stockFeignClient.getFinancialReport(symbol);
    }

    @GetMapping("/news")
    public List<CompanyNews> getCompanyNews(@RequestParam String symbol,
                                            @RequestParam String from,
                                            @RequestParam String to){
        return stockFeignClient.getCompanyNews(symbol, from, to);
    }

    @GetMapping("/shares")
    public CompanyShares getCompanyShares(@RequestParam String symbol){
        return stockFeignClient.getCompanyShares(symbol);
    }

    @GetMapping("/candles")
    public CandleDto getCompanyCandle(@RequestParam String symbol){
        return stockFeignClient.getCompanyCandle(symbol);
    }

}