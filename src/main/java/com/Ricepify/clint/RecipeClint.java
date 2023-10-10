package com.Ricepify.clint;

import com.Ricepify.bo.MealBO;
import com.Ricepify.bo.MealsBO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RecipeClint {

    private final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/random.php";

    private final String API_URL_ID = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=";


    public MealBO getRandomMealInfo() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealsBO> response = restTemplate.getForEntity(API_URL, MealsBO.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) ||
                !response.hasBody()) {
            throw new IOException("There is an issue while call the external API");
        }

        return response.getBody().getMealBOList().get(0);
    }

    public MealBO getMealInfoById(int id) throws IOException {


        // Create a RestTemplate instance to make HTTP requests.
        RestTemplate restTemplate = new RestTemplate();

        // Make an HTTP GET request to the API endpoint with the specified ID.
        ResponseEntity<MealsBO> response = restTemplate.getForEntity((API_URL_ID + id), MealsBO.class);

        // Check the response status code and whether it has a body.
        if (!response.getStatusCode().equals(HttpStatus.OK) || !response.hasBody()) {
            throw new IOException("There is an issue while calling the external API");
        }

        // Assuming MealsBO contains a list of MealBO objects, return the first meal.
        return response.getBody().getMealBOList().get(0);
    }
    public MealBO getMealInfo() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealsBO> response = restTemplate.getForEntity(API_URL, MealsBO.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) ||
                !response.hasBody()) {
            throw new IOException("There is an issue while call the external API");
        }

        return response.getBody().getMealBOList().get(0);
    }


    // create method to get all category.
    public MealBO getAllCategoryInfo() throws IOException {

        String  getAllCategoryUrl = UriComponentsBuilder.fromHttpUrl(baseUrl +"list.php?c=list")
                .build()
                .toUriString();


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealsBO> response = restTemplate.getForEntity(getAllCategoryUrl, MealsBO.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) ||
                !response.hasBody()) {
            throw new IOException("There is an issue while call the external API");
        }

        return response.getBody().getMealBOList().get(0);
    }


    // create method to get all area .
    public MealBO getAllAreasInfo() throws IOException {

        String  getAllAreasUrl = UriComponentsBuilder.fromHttpUrl(baseUrl +"list.php?a=list")
                .build()
                .toUriString();


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealsBO> response = restTemplate.getForEntity(getAllAreasUrl, MealsBO.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) ||
                !response.hasBody()) {
            throw new IOException("There is an issue while call the external API");
        }

        return response.getBody().getMealBOList().get(0);
    }










}
