package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
}
