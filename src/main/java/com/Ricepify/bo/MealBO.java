package com.Ricepify.bo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MealBO {

    @JsonProperty("idMeal")
    private String id ;

    @JsonProperty("strMeal")
    private String mealName;

    @JsonProperty("strYoutube")
    private String video;

    @JsonProperty("strInstructions")
    private String instructions;

    @JsonProperty("strMealThumb")
    private String image;

    @JsonProperty("strCategory")
    private String category;

    @JsonProperty("strArea")
    private String area;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "MealBO{" +
                "id='" + id + '\'' +
                ", mealName='" + mealName + '\'' +
                ", video='" + video + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
