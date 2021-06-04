package com.iteachart.model.service;

import com.iteachart.model.entity.CompanyNews;

import java.time.LocalDate;
import java.util.List;

public interface CompanyNewsService {

    List<CompanyNews> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
