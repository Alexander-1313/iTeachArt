package com.iteachart.stock.service;

import com.iteachart.stock.dto.CompanyDto;
import com.iteachart.stock.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findBySymbol(String symbol);

    List<Company> findAllByCountry(String country);
}
