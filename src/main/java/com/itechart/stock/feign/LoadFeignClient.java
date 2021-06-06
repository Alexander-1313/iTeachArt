package com.itechart.stock.feign;

import com.itechart.stock.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "load-server", url = "localhost:9090")
public interface LoadFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Company> getAllCompanies(@RequestParam String exchange);

    @GetMapping( "/company")
    Company getCompany(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/financialReports")
    List<FinancialReport> getFinancialReport(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    List<CompanyNews> getCompanyNews(@RequestParam String symbol,
                                     @RequestParam String from,
                                     @RequestParam String to);

    @RequestMapping(method = RequestMethod.GET, value = "/shares")
    List<CompanyShares> getCompanyShares(@RequestParam String symbol);

    @RequestMapping(method = RequestMethod.GET, value = "/candles")
    List<Candle> getCompanyCandle(@RequestParam String symbol,
                            @RequestParam String from,
                            @RequestParam String to);

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    String updateStock();
}
