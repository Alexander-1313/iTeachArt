package com.itechart.stock.controller;

import com.itechart.stock.entity.Candle;
import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyNews;
import com.itechart.stock.entity.CompanyShares;
import com.itechart.stock.entity.FinancialReport;
import com.itechart.stock.feign.LoadFeignClient;
import com.itechart.stock.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StockController {

    private final UserValidationService userValidationService;
    private final LoadFeignClient loadFeignClient;

    @GetMapping("/all")
    public List<Company> getAllCompanies(@RequestParam String exchange, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getAllCompanies(exchange);
    }

    @GetMapping("/company")
    public Company getCompany(@RequestParam String symbol, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getCompany(symbol);
    }

    @GetMapping("/financialReports")
    public List<FinancialReport> getFinancialReport(@RequestParam String symbol, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getFinancialReport(symbol);
    }

    @GetMapping("/news")
    public List<CompanyNews> getCompanyNews(@RequestParam String symbol,
                                            @RequestParam String from,
                                            @RequestParam String to, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getCompanyNews(symbol, from, to);
    }

    @GetMapping("/shares")
    public List<CompanyShares> getCompanyShares(@RequestParam String symbol, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getCompanyShares(symbol);
    }

    @GetMapping("/candles")
    public List<Candle> getCompanyCandle(@RequestParam String symbol,
                                         @RequestParam String from,
                                         @RequestParam String to, Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.getCompanyCandle(symbol, from, to);
    }

    @GetMapping("/update")
    public String getCompanyCandle(Principal principal) {
        if (userValidationService.isBlocked(principal.getName())) {
            log.info("user with email={} is blocked", principal.getName());
            return null;
        }
        return loadFeignClient.updateStock();
    }
}