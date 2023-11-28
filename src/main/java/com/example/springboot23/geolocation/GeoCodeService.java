package com.example.springboot23.geolocation;

import com.example.springboot23.ConfigProperties;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@EnableRetry
public class GeoCodeService {

    RestClient restClient;
    RestTemplate restTemplate;

    final ConfigProperties configProperties;

//    public GeoCodeService(RestTemplateBuilder restClient) {
//        restTemplate = restClient.build();
//    }

    public GeoCodeService(RestClient.Builder restClientBuilder, ConfigProperties configProperties) {
        this.configProperties = configProperties;
        System.out.println(configProperties.geo_base_url());
        this.restClient = restClientBuilder.baseUrl(configProperties.geo_base_url()).build();
    }

    @Retryable(retryFor = {RestClientException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public String reverseGeoCode(float lat, float lon) {
//        final String uri = "https://geocode.maps.co/reverse?lat=55.0&lon=16.5";
//        return restTemplate.getForObject(uri, String.class);
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/reverse")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .build())
                .retrieve()
                .body(String.class);
    }

    @Recover
    public String recoverMethod(RestClientException e) {
        return "Error, couldn't get value";
    }
}
