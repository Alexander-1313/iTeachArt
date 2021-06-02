package com.iteachart.model.dto;

import com.iteachart.model.entity.Candle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandleDto {

    private List<BigDecimal> c;
    private List<BigDecimal> h;
    private List<BigDecimal> l;
    private List<BigDecimal> o;
    private String s;
    private List<Long> t;
    private List<Long> v;

    public static List<Candle> fromDtoToEntity(CandleDto candleDto){
        if(candleDto.getT() == null) return null;

        List<Candle> candles = new ArrayList<>();
        int size = candleDto.getT().size();

        for(int i = 0; i < size; i++){
            Candle candle = new Candle();
            candle.setLowPrice(candleDto.getL().get(i));
            candle.setClosePrice(candleDto.getC().get(i));
            candle.setHighPrice(candleDto.getH().get(i));
            candle.setOpenPrice(candleDto.getO().get(i));
            candle.setDatetime(LocalDate.ofInstant(Instant.ofEpochSecond(candleDto.getT().get(i)), ZoneId.systemDefault()));
            candle.setVolumeData(candleDto.getV().get(i));
            candles.add(candle);
        }

        return candles;
    }
}
