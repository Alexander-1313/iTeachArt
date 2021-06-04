package com.iteachart.model.service;

import com.iteachart.model.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findBySymbol(String symbol);

    List<Company> findAllByCountry(String country);
}
