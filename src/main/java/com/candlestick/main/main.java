package com.candlestick.main;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Choose instrument(default: BTC_USDT): ");
        String instrument_name = keyboard.nextLine();
        if (instrument_name.equals("")) {
            instrument_name = "BTC_USDT";
        }
        System.out.print("Choose timeframe(default: 1m, now just support 1m): ");
        String timeframe = keyboard.nextLine();
        timeframe = "1m";
        System.out.printf("Instrument name: %s, timeframe: %s%n", instrument_name, timeframe);
        System.out.printf("CandleStick consistency verify result: %b%n", VerifyService.callAPI(instrument_name, timeframe));
    }
}
