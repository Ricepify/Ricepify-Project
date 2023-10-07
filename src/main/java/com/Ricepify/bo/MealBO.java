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




    //Setters


    public void setId(String id) {
        this.id = id;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setArea(String area) {
        this.area = area;
    }
    //Getters


    public String getId() {
        return id;
    }

    public String getMealName() {
        return mealName;
    }

    public String getVideo() {
        return video;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
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
