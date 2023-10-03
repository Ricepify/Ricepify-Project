package com.Ricepify.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MealsBO {

    @JsonProperty("meals")
    private List<MealBO> mealBOList;

    public List<MealBO> getMealBOList() {
        return mealBOList;
    }

    public void setMealBOList(List<MealBO> mealBOList) {
        this.mealBOList = mealBOList;
    }

    @Override
    public String toString() {
        return "MealsBO{" +
                "mealBOList=" + mealBOList +
                '}';
    }
}
