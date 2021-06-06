package com.itechart.load.service;

import com.itechart.load.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findBySymbol(String symbol);

    List<Company> findAllByCountry(String country);
}
