package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Company;
import com.iteachart.stock.entity.CompanyShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanySharesRepository extends JpaRepository<CompanyShares, Long> {

    List<CompanyShares> findAllByCompanySharesCompany(Company company);
}
