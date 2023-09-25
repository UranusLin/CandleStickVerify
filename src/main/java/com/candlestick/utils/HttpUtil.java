package com.candlestick.utils;

import com.candlestick.models.CandleStickResponse;
import com.candlestick.models.TradeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtil {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static URL url;
    private static int status;
    private static BufferedReader in;
    private static String inputLine;

    public static CandleStickResponse apiCandleStickGet(String Url, Map<String, String> parameters) throws IOException {
        url = new URL(Url + "?" + getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(false);
        return getResponse(con);
    }

    public static CandleStickResponse getResponse(HttpURLConnection con) throws IOException {
        status = con.getResponseCode();
        if (status != 200) {
            System.out.println("Call candle stick error code: " + status);
            return null;
        } else {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(String.valueOf(content), CandleStickResponse.class);
        }
    }

    public static TradeResponse apiTradeGet(String Url, Map<String, String> parameters) throws IOException {
        url = new URL(Url + "?" + getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(false);
        return getTradeResponse(con);
    }

    public static TradeResponse getTradeResponse(HttpURLConnection con) throws IOException {
        status = con.getResponseCode();
        if (status != 200) {
            System.out.println("Call trade error code: " + status);
            return null;
        } else {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(String.valueOf(content), TradeResponse.class);
        }
    }


    public static String getParamsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
