package com.iteachart.model.repository;

import com.iteachart.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Company findByTicker(String ticker);
    
    List<Company> findAllByCountry(String country);

    Company findByName(String name);
}
