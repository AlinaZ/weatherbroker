package ru.bellintegrator.yahoo_weather.service;

import ru.bellintegrator.weatherparser.Result;

import java.io.IOException;


public interface YahooRequestInterface {
    Result request(String city, String region) throws IOException;
}
