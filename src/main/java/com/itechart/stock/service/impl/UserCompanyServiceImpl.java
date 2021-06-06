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

        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() == 2) {
            log.info("cant add company to user with email={}, because user isn't subscribed", email);
            return null;
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
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return userByEmail.getCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return loadFeignClient.getCompany(company);
    }

    @Override
    public List<FinancialReport> getFinancialReportByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return loadFeignClient.getFinancialReport(company);
    }

    @Override
    public List<Candle> getCandleByCompany(String email, String company, String from, String to) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return loadFeignClient.getCompanyCandle(company, from, to);
    }

    @Override
    public List<CompanyShares> getSharesByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return loadFeignClient.getCompanyShares(company);
    }

    @Override
    public List<CompanyNews> getNewsByCompany(String email, String company, String from, String to) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() > 2) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return loadFeignClient.getCompanyNews(company, from, to);
    }

    @Override
    public User removeCompanyFromUser(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        Company companyByName = companyRepository.findByName(company);
        userByEmail.getCompanies().removeIf(c -> c.getTicker().equals(company));
        return userRepository.save(userByEmail);
    }
}
