package com.itechart.load.repository;

import com.itechart.load.entity.Company;
import com.itechart.load.entity.CompanyShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanySharesRepository extends JpaRepository<CompanyShares, Long> {
    List<CompanyShares> findAllByCompanySharesCompany(Company company);
}
