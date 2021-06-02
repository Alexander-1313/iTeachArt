package com.iteachart.model.service;

import com.iteachart.model.entity.FinancialReport;

import java.util.List;

public interface FinancialReportService {

    List<FinancialReport> findAllBySymbol(String symbol);
}
