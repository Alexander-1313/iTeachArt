package com.itechart.load.feign;

import com.itechart.load.dto.CandleDto;
import com.itechart.load.dto.CompanyDto;
import com.itechart.load.entity.Company;
import com.itechart.load.entity.CompanyNews;
import com.itechart.load.entity.CompanyShares;
import com.itechart.load.entity.FinancialReport;
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

    @RequestMapping(method = RequestMethod.GET, value = "/stock/candle?resolution=1&token=btqbebn48v6t9hdd6cog")
    CandleDto getCompanyCandle(@RequestParam String symbol,
                               @RequestParam Long from,
                               @RequestParam Long to);
}
