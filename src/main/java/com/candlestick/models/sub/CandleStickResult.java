package com.candlestick.models.sub;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CandleStickResult {
    String instrument_name;
    String depth;
    String interval;
    List<CandleStickData> data;
}
