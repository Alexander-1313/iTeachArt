package com.itechart.stock.service;

import com.itechart.stock.entity.FinancialReport;

import java.util.List;

public interface FinancialReportService {

    List<FinancialReport> findAllBySymbol(String symbol);
}
