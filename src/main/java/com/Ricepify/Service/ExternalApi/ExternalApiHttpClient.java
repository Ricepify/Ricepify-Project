package com.Ricepify.Service.ExternalApi;

import com.Ricepify.bo.externalAPI.MealResponse;

import java.util.List;

public interface ExternalApiHttpClient {
    List<MealResponse> sendRequest(String apiUrl);
}
