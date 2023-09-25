## How to use
```
java -jar ./out/artifacts/CandleStickVerify/CandleStickVerify.jar
Choose instrument(default: BTC_USDT): 
Choose timeframe(default: 1m, now just support 1m): 
```
result:
```
Instrument name: BTC_USDT, timeframe: 1m
Verify candlestick is: CandleStickData(t=1637658180000, o=56359.22, h=56396.96, l=56345.39, c=56396.96, v=38.03756)
Trade data size: 106
System calculate candlestick is: CandleStickData(t=0, o=56381.46, h=56396.96, l=56369.62, c=56376.24, v=0.0)
CandleStick consistency verify result: false
```