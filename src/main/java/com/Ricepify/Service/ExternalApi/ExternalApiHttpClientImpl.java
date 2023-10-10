package com.Ricepify.Service.ExternalApi;

import com.Ricepify.bo.externalAPI.ApiResponse;
import com.Ricepify.bo.externalAPI.MealResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalApiHttpClientImpl implements ExternalApiHttpClient{
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public ExternalApiHttpClientImpl(CloseableHttpClient client, ObjectMapper objectMapper,
                                     @Value("${external.api.baseurl}") String baseUrl) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<MealResponse> sendRequest(String apiUrl) {
        try {
            HttpGet request = new HttpGet(baseUrl + apiUrl);
            CloseableHttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                ApiResponse apiResponse = objectMapper.readValue(responseBody, ApiResponse.class);
                return apiResponse.getMeals();
            } else {
                // Handle non-200 status code
                return new ArrayList<>();
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
