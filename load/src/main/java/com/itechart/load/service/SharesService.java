package com.itechart.load.service;

import com.itechart.load.entity.CompanyShares;

import java.util.List;

public interface SharesService {

    List<CompanyShares> findAllBySymbol(String Symbol);
}
