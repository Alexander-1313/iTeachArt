package com.iteachart.web.controller;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import com.iteachart.model.entity.CompanyShares;
import com.iteachart.model.entity.FinancialReport;
import com.iteachart.model.entity.User;
import com.iteachart.model.service.UserCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCompanyService userCompanyService;

    @PostMapping("/user/addCompanyToUser")
    public User addCompanyToUser(@RequestParam("company") String companyName, Principal principal){
        System.out.println("principal.getName() = " + principal.getName());
        return userCompanyService.addCompanyToUser(companyName, principal.getName());
    }

    @GetMapping("/user/all")
    public Set<Company> allCompanies(Principal principal){
        return userCompanyService.getAllCompaniesByUser(principal.getName());
    }

    @GetMapping("/user/company")
    public Company getCompanyByTicker(@RequestParam("company") String company, Principal principal){
        return userCompanyService.getCompanyBySymbol(principal.getName(), company);
    }

    @GetMapping("/user/financialReports")
    public List<FinancialReport> getFinancialReport(@RequestParam("company") String company, Principal principal){
        return userCompanyService.getFinancialReportByCompany(principal.getName(), company);
    }

    @GetMapping("/user/candle")
    public List<Candle> getCompanyCandle(@RequestParam("company") String company, Principal principal){
        return userCompanyService.getCandleByCompany(principal.getName(), company);
    }

    @GetMapping("/user/shares")
    public List<CompanyShares> getCompanyShares(@RequestParam("company") String company, Principal principal){
        return userCompanyService.getSharesByCompany(principal.getName(), company);
    }

    @GetMapping("/user/news")
    public List<CompanyNews> getCompanyNews(@RequestParam("company") String company, Principal principal){
        return userCompanyService.getNewsByCompany(principal.getName(), company);
    }
}
