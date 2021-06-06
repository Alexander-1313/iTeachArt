package com.itechart.stock.repository;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.CompanyShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanySharesRepository extends JpaRepository<CompanyShares, Long> {

    List<CompanyShares> findAllByCompanySharesCompany(Company company);
}
