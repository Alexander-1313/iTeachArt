package com.iteachart.model.service;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import com.iteachart.model.entity.CompanyShares;
import com.iteachart.model.entity.FinancialReport;
import com.iteachart.model.entity.User;

import java.util.List;
import java.util.Set;

public interface UserCompanyService {

    User addCompanyToUser(String company, String email);

    Set<Company> getAllCompaniesByUser(String email);

    Company getCompanyBySymbol(String email, String symbol);

    List<FinancialReport> getFinancialReportByCompany(String email, String company);

    List<Candle> getCandleByCompany(String email, String company);

    List<CompanyShares> getSharesByCompany(String email, String company);

    List<CompanyNews> getNewsByCompany(String email, String company);

    User removeCompanyFromUser(String email, String symbol);
}
