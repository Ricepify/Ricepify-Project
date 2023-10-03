package com.Ricepify.Models;


public class RandomMealEntity {
    private String mealName;
    private String video;
    private String instructions;
    private String image;
    private String category;
    private String area;
    private String id;
    public RandomMealEntity() {
    }

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

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getArea() {
        return area;
    }
}
