package com.iteachart.model.service;

import com.iteachart.model.entity.CompanyShares;

import java.util.List;

public interface SharesService {

    List<CompanyShares> findAllBySymbol(String Symbol);
}
