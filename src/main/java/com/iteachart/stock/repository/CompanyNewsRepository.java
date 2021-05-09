package com.iteachart.stock.repository;

import com.iteachart.stock.entity.CompanyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyNewsRepository extends JpaRepository<CompanyNews, Long> {
}
