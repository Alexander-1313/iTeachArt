package com.itechart.stock.service;

import com.itechart.stock.entity.*;

import java.util.List;
import java.util.Set;

public interface UserCompanyService {

    User addCompanyToUser(String company, String email);

    Set<Company> getAllCompaniesByUser(String email);

    Company getCompanyBySymbol(String email, String symbol);

    List<FinancialReport> getFinancialReportByCompany(String email, String company);

    List<Candle> getCandleByCompany(String email, String company, String from, String to);

    List<CompanyShares> getSharesByCompany(String email, String company);

    List<CompanyNews> getNewsByCompany(String email, String company, String from, String to);

    User removeCompanyFromUser(String email, String symbol);
}
