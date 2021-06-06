package com.itechart.stock.repository;

import com.itechart.stock.entity.Candle;
import com.itechart.stock.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findAllByCandleCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
