package com.itechart.stock.service;

import com.itechart.stock.entity.CompanyShares;

import java.util.List;

public interface SharesService {

    List<CompanyShares> findAllBySymbol(String Symbol);
}
