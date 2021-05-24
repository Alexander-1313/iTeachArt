package com.iteachart.stock.feign;


import com.iteachart.stock.dto.CandleDto;
import com.iteachart.stock.dto.CompanyDto;
import com.iteachart.stock.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "stock-service-proxy", url = "https://finnhub.io/api/v1")
public interface StockFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/stock/symbol?token=btqbebn48v6t9hdd6cog")
    List<CompanyDto> getAllCompanies(@RequestParam String exchange);
    
    @GetMapping( "/stock/profile2?token=btqbebn48v6t9hdd6cog")
    Company getCompany(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/stock/financials-reported?token=btqbebn48v6t9hdd6cog")
    FinancialReport getFinancialReport(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/company-news?token=btqbebn48v6t9hdd6cog")
    List<CompanyNews> getCompanyNews(@RequestParam String symbol,
                                     @RequestParam String from,
                                     @RequestParam String to);

    @RequestMapping(method = RequestMethod.GET, value = "/stock/profile2?token=btqbebn48v6t9hdd6cog")
    CompanyShares getCompanyShares(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/stock/candle?resolution=M&token=btqbebn48v6t9hdd6cog")
    CandleDto getCompanyCandle(@RequestParam String symbol,
                               @RequestParam Long from,
                               @RequestParam Long to);
}
