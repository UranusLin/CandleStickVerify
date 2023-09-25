package com.candlestick.models.sub;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandleStickData {
    // Unix timestamp
    long t;
    // open
    float o;
    // high
    float h;
    // low
    float l;
    // close
    float c;
    // volume
    float v;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CandleStickData candleStickData = (CandleStickData) obj;
        return o == candleStickData.o &&
                h == candleStickData.h &&
                l == candleStickData.l &&
                c == candleStickData.c;
    }
}
