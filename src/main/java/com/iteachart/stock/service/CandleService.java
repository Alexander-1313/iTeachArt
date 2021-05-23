package com.iteachart.stock.service;

import com.iteachart.stock.entity.Candle;

import java.time.LocalDate;
import java.util.List;

public interface CandleService {

    List<Candle> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
