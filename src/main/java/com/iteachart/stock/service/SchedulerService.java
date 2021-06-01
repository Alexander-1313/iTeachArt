package com.iteachart.stock.service;

import com.iteachart.stock.dto.CandleDto;
import com.iteachart.stock.dto.CompanyDto;
import com.iteachart.stock.entity.*;
import com.iteachart.stock.feign.StockFeignClient;
import com.iteachart.stock.repository.*;
import com.iteachart.stock.util.StringConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final UserRepository userRepository;
    private final StockFeignClient stockFeignClient;
    private final MailService mailService;
    private final CompanyRepository companyRepository;

    @Scheduled(fixedRate = 1000000)
    public void loadDataFromStock() {
        List<CompanyDto> us = stockFeignClient.getAllCompanies("US");

        for(int i =0; i < 5; i++) {
            String symbol = us.get(i).getSymbol();
            Company company = stockFeignClient.getCompany(symbol);
            if(company.getTicker() == null) continue;

            CandleDto companyCandle = stockFeignClient.getCompanyCandle(symbol, LocalDate.now().minusDays(100).toEpochSecond(LocalTime.MAX, ZoneOffset.UTC), LocalDate.now().toEpochSecond(LocalTime.MAX, ZoneOffset.UTC));
            FinancialReport financialReport = stockFeignClient.getFinancialReport(symbol);
            CompanyShares companyShares = stockFeignClient.getCompanyShares(symbol);
            List<CompanyNews> companyNews = stockFeignClient.getCompanyNews(symbol, LocalDate.now().minusDays(1).toString(), LocalDate.now().toString());

            financialReport.setFinancialReportCompany(company);
            List<FinancialReport> companyFinancialReports = company.getFinancialReports();
            companyFinancialReports.add(financialReport);
            company.setFinancialReports(companyFinancialReports);

            companyShares.setCompanySharesCompany(company);
            List<CompanyShares> companySharesList = company.getCompanyShares();
            companySharesList.add(companyShares);
            company.setCompanyShares(companySharesList);

            for (CompanyNews news : companyNews) {
                news.setCompanyNewsCompany(company);
                news.setDatetime(LocalDate.now().minusDays(1));
            }
            List<CompanyNews> companyNewsList = company.getCompanyNews();
            companyNewsList.addAll(companyNews);
            company.setCompanyNews(companyNewsList);

            List<Candle> candles = CandleDto.fromDtoToEntity(companyCandle);
            if (candles != null) {
                for (Candle candle : candles) {
                    candle.setCandleCompany(company);
                }
                List<Candle> candleList = company.getCandles();
                candleList.addAll(candles);
                company.setCandles(candleList);
            }

            Company save = companyRepository.save(company);

            log.info("company={} was saved to DB", save);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void notifyUserAboutSubscribe(){
        List<User> allUsers = userRepository.findAll();
        for(User user: allUsers){
            if(user.getSubscribeExpireDate().isAfter(LocalDate.now())){
                mailService.sendEmail(user.getEmail(), StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
                user.setIsBlocked(true);
                log.info("notify user with email={} about his end subscribe", user.getEmail());
            }else if(LocalDate.now().minusDays(2L).isAfter(user.getSubscribeExpireDate())){
                mailService.sendEmail(user.getEmail(), StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
                log.info("notify user with={} about soon ending of subscribe", user.getEmail());
            }else{
                log.info("user with email={} is subscribed", user.getEmail());
            }
        }

    }

}
