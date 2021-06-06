package com.itechart.load.service;

import com.itechart.load.entity.FinancialReport;

import java.util.List;

public interface FinancialReportService {

    List<FinancialReport> findAllBySymbol(String symbol);
}
