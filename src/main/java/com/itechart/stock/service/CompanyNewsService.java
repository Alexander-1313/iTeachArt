package com.itechart.stock.service;

import com.itechart.stock.entity.CompanyNews;

import java.time.LocalDate;
import java.util.List;

public interface CompanyNewsService {

    List<CompanyNews> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
