package com.iteachart.stock.service;

import com.iteachart.stock.entity.FinancialReport;

import java.util.List;

public interface FinancialReportService {

    List<FinancialReport> findAllBySymbol(String symbol);
}
