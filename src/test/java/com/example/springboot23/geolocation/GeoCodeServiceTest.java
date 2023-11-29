package com.example.springboot23.geolocation;

import com.example.springboot23.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.RetryConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadGateway;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(GeoCodeService.class)
@Import(RetryConfiguration.class)
class GeoCodeServiceTest {

    @Autowired
    private GeoCodeService service;

    @Autowired
    private MockRestServiceServer server;

    @MockBean
    ConfigProperties configProperties;

    @Test
    void reverseGeoCode() {
        server.expect(ExpectedCount.max(1), requestTo("/reverse?lat=55.0&lon=16.5")).andRespond(withSuccess("Hello", MediaType.APPLICATION_JSON));

        String result = service.reverseGeoCode(55.0f, 16.5f);

        server.verify();
        assertThat(result).isEqualTo("Hello");
    }

    @Test
    void reverseGeoCodeFailsWithError() {
        server.expect(ExpectedCount.max(3), requestTo("/reverse?lat=55.0&lon=16.5")).andRespond(withBadGateway());

        String result = service.reverseGeoCode(55.0f, 16.5f);

        server.verify();
        assertThat(result).isEqualTo("Error, couldn't get value");
    }
}
