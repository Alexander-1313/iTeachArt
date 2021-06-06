package com.itechart.load.repository;

import com.itechart.load.entity.Candle;
import com.itechart.load.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findAllByCandleCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
