package com.Ricepify.Service.ExternalApi;

import com.Ricepify.bo.externalAPI.MealResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    private final ExternalApiHttpClient httpClient;

    public ExternalApiServiceImpl(ExternalApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<MealResponse> searchByCategory(String category) {
        String apiUrl = "/filter.php?c=" + category;
        return httpClient.sendRequest(apiUrl);
    }

    @Override
    public List<MealResponse> searchByArea(String area) {
        String apiUrl = "/filter.php?a=" + area;
        return httpClient.sendRequest(apiUrl);
    }
}
