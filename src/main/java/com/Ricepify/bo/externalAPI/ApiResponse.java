package com.Ricepify.bo.externalAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResponse {

    @JsonProperty("meals")
    private List<MealResponse> meals;

    public List<MealResponse> getMeals() {
        return meals;
    }
}
