package com.candlestick.models.sub;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TradeResult {
    String instrument_name;
    List<TradeData> data;
}
