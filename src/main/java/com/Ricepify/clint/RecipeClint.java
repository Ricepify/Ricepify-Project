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


    @Deprecated
    public MealBO getOldRandomMealInfo() {

        try {
            // Create a URL object with the API endpoint
            URL url = new URL(API_URL);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response using Gson
                Gson gson = new Gson();
                JsonObject jsonData = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonObject mealObject = jsonData.getAsJsonArray("meals").get(0).getAsJsonObject();

                // Extract and map the fields to a Java object
                MealBO mealBO = new MealBO();
                mealBO.setMealName(mealObject.get("strMeal").getAsString());
                mealBO.setCategory(mealObject.get("strCategory").getAsString());
                mealBO.setVideo(mealObject.get("strYoutube").getAsString());
                mealBO.setInstructions(mealObject.get("strInstructions").getAsString());
                mealBO.setImage(mealObject.get("strMealThumb").getAsString());
                mealBO.setArea(mealObject.get("strArea").getAsString());
                mealBO.setId(mealObject.get("idMeal").getAsString());

                return mealBO;

            } else {
                System.err.println("HTTP Request Failed with error code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public MealBO getRandomMealInfo() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealsBO> response = restTemplate.getForEntity(API_URL, MealsBO.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) ||
                !response.hasBody()) {
            throw new IOException("There is an issue while call the external API");
        }

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
