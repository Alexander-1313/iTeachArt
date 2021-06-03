package com.iteachart.web.controller;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import com.iteachart.model.entity.CompanyShares;
import com.iteachart.model.entity.FinancialReport;
import com.iteachart.model.service.CandleService;
import com.iteachart.model.service.CompanyNewsService;
import com.iteachart.model.service.CompanyService;
import com.iteachart.model.service.FinancialReportService;
import com.iteachart.model.service.SharesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StockController {

    private final CompanyService companyService;
    private final FinancialReportService financialReportService;
    private final SharesService sharesService;
    private final CompanyNewsService companyNewsService;
    private final CandleService candleService;

    @GetMapping("/all")
    public List<Company> getAllCompanies(@RequestParam String exchange){
        return companyService.findAllByCountry(exchange);
    }

    @GetMapping("/company")
    public Company getCompany(@RequestParam String symbol){
        return companyService.findBySymbol(symbol);
    }

    @GetMapping("/financialReports")
    public List<FinancialReport> getFinancialReport(@RequestParam String symbol){
        return financialReportService.findAllBySymbol(symbol);
    }

    @GetMapping("/news")
    public List<CompanyNews> getCompanyNews(@RequestParam String symbol,
                                            @RequestParam String from,
                                            @RequestParam String to){
        return companyNewsService.findAllInPeriod(symbol, LocalDate.parse(from), LocalDate.parse(to));
    }

    @GetMapping("/shares")
    public List<CompanyShares> getCompanyShares(@RequestParam String symbol){
        return sharesService.findAllBySymbol(symbol);
    }

    @GetMapping("/candles")
    public List<Candle> getCompanyCandle(@RequestParam String symbol,
                                         @RequestParam String from,
                                         @RequestParam String to){
        return candleService.findAllInPeriod(symbol, LocalDate.parse(from), LocalDate.parse(to));
    }

}