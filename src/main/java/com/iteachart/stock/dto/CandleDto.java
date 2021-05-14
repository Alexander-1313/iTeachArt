package com.iteachart.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandleDto {

    private List<String> c;
    private List<String> h;
    private List<String> l;
    private List<String> o;
    private String s;
    private List<String> t;
    private List<String> v;
}
