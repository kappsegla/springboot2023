package com.example.springboot23.matchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyMatchers {

    private final ObjectMapper objectMapper;

    public ResponseBodyMatchers() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public <T> ResultMatcher containsObjectAsJson(
            Object expectedObject,
            Class<T> targetClass) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, targetClass);
            assertThat(actualObject).usingRecursiveComparison().isEqualTo(expectedObject);
        };
    }

    public ResultMatcher containsError(
            String expectedFieldName,
            String expectedMessage) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            HashMap<String, Object> errorResult = objectMapper.readValue(json, HashMap.class);
            List<Map.Entry<String, Object>> fieldErrors = errorResult.entrySet().stream()
                    .filter(fieldError -> fieldError.getKey().equals(expectedFieldName))
                    .filter(fieldError -> fieldError.getValue().equals(expectedMessage))
                    .collect(Collectors.toList());

            assertThat(fieldErrors)
                    .hasSize(1)
                    .withFailMessage("expecting exactly 1 error message"
                                     + "with field name '%s' and message '%s'",
                            expectedFieldName,
                            expectedMessage);
        };
    }

    public static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }

}
