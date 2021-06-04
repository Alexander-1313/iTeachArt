package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import com.iteachart.model.entity.CompanyNews;
import com.iteachart.model.entity.CompanyShares;
import com.iteachart.model.entity.FinancialReport;
import com.iteachart.model.entity.User;
import com.iteachart.model.repository.CompanyRepository;
import com.iteachart.model.repository.UserRepository;
import com.iteachart.model.service.UserCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCompanyServiceImpl implements UserCompanyService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public User addCompanyToUser(String company, String email) {
        Company companyByName = companyRepository.findByName(company);
        User userByEmail = userRepository.findByEmail(email);

        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() == 2) {
            log.info("cant add company to user with email={}, because user isn't subscribed", email);
            return null;
        }

        Set<User> users = companyByName.getUsers();
        users.add(userByEmail);
        companyByName.setUsers(users);

        Set<Company> companies = userByEmail.getCompanies();
        companies.add(companyByName);
        userByEmail.setCompanies(companies);

        return userRepository.save(userByEmail);
    }

    @Override
    public Set<Company> getAllCompaniesByUser(String email) {
        User userByEmail = userRepository.findByEmail(email);
        System.out.println("userByEmail.getCompanies().size() = " + userByEmail.getCompanies().size());
        System.out.println("!userByEmail.getSubscribeEnabled() = " + !userByEmail.getSubscribeEnabled());
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return userByEmail.getCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        return userByEmail.getCompanies().stream().filter(c -> c.getName().equals(company)).findFirst().orElse(new Company());
    }

    @Override
    public List<FinancialReport> getFinancialReportByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        Company userCompany = userByEmail.getCompanies().stream().filter(c -> c.getName().equals(company)).findFirst().orElse(new Company());
        return userCompany.getFinancialReports();
    }

    @Override
    public List<Candle> getCandleByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        Company userCompany = userByEmail.getCompanies().stream().filter(c -> c.getName().equals(company)).findFirst().orElse(new Company());
        return userCompany.getCandles();
    }

    @Override
    public List<CompanyShares> getSharesByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        Company userCompany = userByEmail.getCompanies().stream().filter(c -> c.getName().equals(company)).findFirst().orElse(new Company());
        return userCompany.getCompanyShares();
    }

    @Override
    public List<CompanyNews> getNewsByCompany(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        if (!userByEmail.getSubscribeEnabled() && userByEmail.getCompanies().size() >= 3) {
            log.info("user with email={} have so many companies. Please, buy subscribe or remove companies to size 2.", email);
            return null;
        }
        Company userCompany = userByEmail.getCompanies().stream().filter(c -> c.getName().equals(company)).findFirst().orElse(new Company());
        return userCompany.getCompanyNews();
    }

    @Override
    public User removeCompanyFromUser(String email, String company) {
        User userByEmail = userRepository.findByEmail(email);
        Company companyByName = companyRepository.findByName(company);
        userByEmail.getCompanies().remove(companyByName);
        User save = userRepository.save(userByEmail);
        return save;
    }
}
