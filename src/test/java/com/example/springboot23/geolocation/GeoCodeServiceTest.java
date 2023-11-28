package com.example.springboot23.geolocation;

import com.example.springboot23.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(GeoCodeService.class)
class GeoCodeServiceTest {

    @Autowired
    private GeoCodeService service;

    @Autowired
    private MockRestServiceServer server;

    @MockBean
    ConfigProperties configProperties;

    @Test
    void reverseGeoCode() {
        server.expect(requestTo("/reverse?lat=55.0&lon=16.5")).andRespond(withSuccess("Hello", MediaType.APPLICATION_JSON));

        String result = service.reverseGeoCode(55.0f,16.5f);

        server.verify();
        assertThat(result).isEqualTo("Hello");
    }
}
