package com.candlestick.main;

import com.candlestick.models.CandleStickResponse;
import com.candlestick.models.TradeResponse;
import com.candlestick.models.sub.CandleStickData;
import com.candlestick.models.sub.TradeData;
import com.candlestick.utils.HttpUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VerifyService {

    // crypto url
    private static final String cryptoUrl = "https://api.crypto.com/v2/public/";
    // crypto get candlestick
    private static final String getCandlestick = cryptoUrl + "get-candlestick";
    // crypto get trades
    private static final String getTrades = cryptoUrl + "get-trades";

    public static boolean callAPI(String instrument_name, String timeframe) throws IOException {
        // get candlestick
        Map<String, String> parameters = new HashMap<>();
        parameters.put("instrument_name", instrument_name);
        parameters.put("timeframe", timeframe);
        // get trade data
        TradeResponse tradeResponse = HttpUtil.apiTradeGet(getTrades, parameters);
        // get candlestick data
        CandleStickResponse candleStick = HttpUtil.apiCandleStickGet(getCandlestick, parameters);
        // get one last candlestick
        CandleStickData origData = candleStick.getResult().getData().get(candleStick.getResult().getData().size() -1);
        System.out.printf("Verify candlestick is: %s%n", origData);

        // get first candle stick trade data
        List<TradeData> tradeDetail = getTradeDetail(origData, tradeResponse);
        System.out.printf("Trade data size: %s%n", tradeDetail.size());
        // get self calculate candlestick
        CandleStickData selfCal = getCandlestickFromTradeData(tradeDetail);
        System.out.printf("System calculate candlestick is: %s%n", selfCal);

        // verify candlestick consistency
        return verifyCandleStickConsistency(origData, selfCal);
    }

    // to get that moment data
    public static List<TradeData> getTradeDetail(CandleStickData data, TradeResponse tradeResponse) {
        long startTime = data.getT();
        long endTime = data.getT() + 60000;
//        System.out.println(tradeResponse.getResult().getData());
        return  tradeResponse.getResult().getData().stream().filter(d -> d.getT() >= startTime && d.getT() < endTime).collect(Collectors.toList());
    }

    // get candlestick from trade data
    public static CandleStickData getCandlestickFromTradeData(List<TradeData> tradeDetail) {
        CandleStickData candleStickData = new CandleStickData();
        switch (tradeDetail.size()) {
            case 0:
                break;
            case 1:
                candleStickData.setO(tradeDetail.get(0).getP());
                candleStickData.setC(tradeDetail.get(0).getP());
                candleStickData.setH(tradeDetail.get(0).getP());
                candleStickData.setL(tradeDetail.get(0).getP());
            case 2:
                candleStickData.setO(tradeDetail.get(1).getP());
                candleStickData.setC(tradeDetail.get(0).getP());
                candleStickData.setH(tradeDetail.stream().max(Comparator.comparing(TradeData::getP)).get().getP());
                candleStickData.setL(tradeDetail.stream().min(Comparator.comparing(TradeData::getP)).get().getP());
            default:
                candleStickData.setO(tradeDetail.get(tradeDetail.size() - 1).getP());
                candleStickData.setC(tradeDetail.get(0).getP());
                candleStickData.setH(tradeDetail.stream().max(Comparator.comparing(TradeData::getP)).get().getP());
                candleStickData.setL(tradeDetail.stream().min(Comparator.comparing(TradeData::getP)).get().getP());
        }
        return candleStickData;
    }

    // verify CandleStick consistency
    public static boolean verifyCandleStickConsistency(CandleStickData orig, CandleStickData selfCal) {
        return orig.equals(selfCal);
    }
}
