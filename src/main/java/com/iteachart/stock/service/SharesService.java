package com.iteachart.stock.service;

import com.iteachart.stock.entity.CompanyShares;

import java.util.List;

public interface SharesService {

    List<CompanyShares> findAllBySymbol(String Symbol);
}
