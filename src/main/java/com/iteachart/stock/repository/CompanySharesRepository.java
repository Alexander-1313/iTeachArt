package com.iteachart.stock.repository;

import com.iteachart.stock.entity.CompanyShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySharesRepository extends JpaRepository<CompanyShares, Long> {
}
