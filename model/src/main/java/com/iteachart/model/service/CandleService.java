package com.iteachart.model.service;

import com.iteachart.model.entity.Candle;

import java.time.LocalDate;
import java.util.List;

public interface CandleService {

    List<Candle> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
