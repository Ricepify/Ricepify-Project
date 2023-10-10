package com.Ricepify.Service.ExternalApi;

import com.Ricepify.bo.externalAPI.MealResponse;

import java.util.List;

public interface ExternalApiService {
   // List<CreateSearchResultRequest> search(String query);

    public List<MealResponse> searchByCategory(String category);

    public List<MealResponse> searchByArea(String area);
}
