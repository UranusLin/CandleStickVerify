package com.candlestick.models;

import com.candlestick.models.sub.CandleStickResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandleStickResponse{
    private String code;
    private String method;
    private CandleStickResult result;
}
