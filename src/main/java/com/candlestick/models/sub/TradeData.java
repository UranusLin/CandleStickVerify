package com.candlestick.models.sub;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeData {
    // can ignore
    String dataTime;
    // trade id
    String d;
    // buy or sell
    String s;
    // Trade price
    float p;
    // Trade quantity
    float q;
    // Trade timestamp
    long t;
}
