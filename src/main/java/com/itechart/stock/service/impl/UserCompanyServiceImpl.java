package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Candle;
import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyNews;
import com.itechart.stock.entity.CompanyShares;
import com.itechart.stock.entity.FinancialReport;
import com.itechart.stock.entity.User;
import com.itechart.stock.feign.LoadFeignClient;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.repository.UserRepository;
import com.itechart.stock.service.UserCompanyService;
import com.itechart.stock.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCompanyServiceImpl implements UserCompanyService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final LoadFeignClient loadFeignClient;

    @Override
    public User addCompanyToUser(String company, String email) {
        Company companyByName = companyRepository.findByName(company);
        User userByEmail = userRepository.findByEmail(email);

        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() == UserUtils.defaultCompanyCount) {
            log.info("cant add company to user with email={}, because user isn't subscribed", email);
            return null;
        }
        if(userByEmail.getSubscribe() != null) {
            if (userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() == userByEmail.getSubscribe().getCompanyCapacity()) {
                log.info("cant add company. Limit capacity in your subscribe.");
                return null;
            }
        }
        if(companyByName == null){
            log.info("company is null! We sent request to another server...");
            companyByName = loadFeignClient.getCompany(company);
        }

        Company saveCompany = companyRepository.save(companyByName);

        Set<User> users = saveCompany.getUsers();
        if(users == null){
            users = new HashSet<>();
        }
        users.add(userByEmail);
        saveCompany.setUsers(users);

        Set<Company> companies = userByEmail.getCompanies();
        companies.add(saveCompany);
        userByEmail.setCompanies(companies);

        return userRepository.save(userByEmail);
    }

    @Override
    public Set<Company> getAllCompaniesByUser(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > UserUtils.defaultCompanyCount) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return userByEmail.getCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String email, String company) {
        if(validateUserAndCompany(email, company) != null) {
            return loadFeignClient.getCompany(company);
        }
        return null;
    }

    @Override
    public List<FinancialReport> getFinancialReportByCompany(String email, String company) {
        if(validateUserAndCompany(email, company) != null) {
            return loadFeignClient.getFinancialReport(company);
        }
        return null;
    }

    @Override
    public List<Candle> getCandleByCompany(String email, String company, String from, String to) {
        if(validateUserAndCompany(email, company) != null){
            return loadFeignClient.getCompanyCandle(company, from, to);
        }
        return null;
    }

    @Override
    public List<CompanyShares> getSharesByCompany(String email, String company) {
        if(validateUserAndCompany(email, company) != null) {
            return loadFeignClient.getCompanyShares(company);
        }
        return null;
    }

    @Override
    public List<CompanyNews> getNewsByCompany(String email, String company, String from, String to) {
        if(validateUserAndCompany(email, company) != null) {
            return loadFeignClient.getCompanyNews(company, from, to);
        }
        return null;
    }

    @Override
    public User removeCompanyFromUser(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        Company companyByName = companyRepository.findByTicker(company);
        if(userByEmail != null && companyByName != null) {
            companyByName.getUsers().removeIf(u -> u.getEmail().equals(email));
            userByEmail.getCompanies().removeIf(c -> c.getTicker().equals(company));
        }
        companyRepository.save(companyByName);
        return userRepository.save(userByEmail);
    }

    private User validateUserAndCompany(String email, String company){
        User userByEmail = userRepository.findByEmail(email);
        Company companyByTicker = companyRepository.findByTicker(company);
        if(companyByTicker == null || !userByEmail.getCompanies().contains(companyByTicker)){
            log.info("user with email={} dont track this company", email);
            return null;
        }
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > UserUtils.defaultCompanyCount) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return userByEmail;
    }
}
