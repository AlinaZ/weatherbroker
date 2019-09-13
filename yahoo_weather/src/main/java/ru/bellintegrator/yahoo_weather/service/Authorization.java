package ru.bellintegrator.yahoo_weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@ApplicationScoped
public class Authorization {

    final String appId = "7zVcYe38";
    final String consumerKey = "dj0yJmk9M3JHQW9VSjBSSVE4JmQ9WVdrOU4zcFdZMWxsTXpnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTQw";
    final String consumerSecret = "9317794e13b152e0d99f66c0d614b9dc684bdc11";
    final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

    private final Logger LOG= LoggerFactory.getLogger(Authorization.class);

    public Authorization() {
    }

    public HttpEntity<String> createHeaders(String city, String region) throws UnsupportedEncodingException {

        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = new ArrayList<String>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        try {
            parameters.add("location=" + URLEncoder.encode(city + "," + region, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error encoding parameter", e.getMessage(), e);
        }
        parameters.add("format=json");
        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString = "";
        try {
            signatureString = "GET&" +
                    URLEncoder.encode(url, "UTF-8") + "&" +
                    URLEncoder.encode(parametersList.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) { LOG.error("Error encoding parameter", e.getMessage(), e);
        }

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";


        /*WeatherYdnJava weather=new WeatherYdnJava();*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationLine);
        headers.add("Content-Type", "application/json");
        headers.add("X-Yahoo-App-Id", appId);


        return new HttpEntity<>(headers);

    }
}
