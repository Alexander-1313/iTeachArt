package com.iteachart.model.repository;

import com.iteachart.model.entity.Candle;
import com.iteachart.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findAllByCandleCompanyAndDatetimeBetween(Company company, LocalDate from, LocalDate to);
}
