package ru.bellintegrator.yahoo_weather.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import ru.bellintegrator.weatherparser.Result;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class YahooRequest implements YahooRequestInterface {
    @Inject
    private Authorization auth;

    private final Logger LOG = LoggerFactory.getLogger(YahooRequest.class);

    /*YahooRequest (Authorization auth){
        this.auth=auth;
    }

    YahooRequest (){
    }*/

    @Override
    public Result request(String city, String region) throws IOException {
        ResponseEntity<Result> result;


        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = auth.createHeaders(city, region);
        System.out.println(entity.getBody());
        System.out.println(entity.getHeaders());


        result = restTemplate.exchange(
                "https://weather-ydn-yql.media.yahoo.com/forecastrss?location=" + city + "," + region + "&format=json", HttpMethod.GET, entity,
                Result.class);

        /*result =restTemplate.getForObject("https://weather-ydn-yql.media.yahoo.com/forecastrss?location=" + city + "," + region + "&format=json",Result.class);*/


        /*System.out.println(result.getBody().getClass());
        System.out.println(result.getBody().toString());
        System.out.println(result.getStatusCode());***********************/


        LOG.info("yahoo request status code -> {}", result.getStatusCodeValue());
        LOG.info("yahoo request result -> {}", result.getBody().toString());

        Result weather=result.getBody();

        return weather;

       /* if (result.getStatusCode().equals(HttpStatus.OK)) {
            try {
                Location location = result.getBody().getLocation();
                CurrentObservation currentObservation = result.getBody().getCurrentObservation();
                List<Forecast> forecasts = result.getBody().getForecasts();
            } catch (CustomException e) {
                throw new CustomException("Wrong response");
            }
        } */


    }

    public static void main(String[] args) throws Exception {
       /* YahooRequest request = new YahooRequest();
        request.request("ufa", "ru");*/


    }
}
