package com.itechart.load.service;

import com.itechart.load.entity.Candle;

import java.time.LocalDate;
import java.util.List;

public interface CandleService {

    List<Candle> findAllInPeriod(String symbol, LocalDate from, LocalDate to);
}
