package com.candlestick.models;

import com.candlestick.models.sub.TradeResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeResponse {
    private String code;
    private String method;
    private TradeResult result;
}
