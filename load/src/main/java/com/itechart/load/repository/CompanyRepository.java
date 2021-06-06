package com.itechart.load.repository;

import com.itechart.load.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByTicker(String ticker);

    List<Company> findAllByCountry(String country);
}
