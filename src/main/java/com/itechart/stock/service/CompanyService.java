package com.itechart.stock.service;

import com.itechart.stock.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findBySymbol(String symbol);

    List<Company> findAllByCountry(String country);
}
