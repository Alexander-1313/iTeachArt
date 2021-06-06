package com.itechart.load.service;

import com.itechart.load.entity.CompanyNews;

import java.time.LocalDate;
import java.util.List;

public interface CompanyNewsService {

    List<CompanyNews> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
