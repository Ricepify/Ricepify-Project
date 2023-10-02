package com.Ricepify.Service;


import com.Ricepify.Models.Recipe;
import com.Ricepify.bo.MealsBO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Ricepify.bo.MealBO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealsService {
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

    public List<MealBO> getRandomMeals(int numberOfMeals) throws IOException {
        List<MealBO> mealsList = new ArrayList<>();

        for (int i = 0; i < numberOfMeals; i++) {
            MealBO mealBO = getRandomMealInfo();

            mealsList.add(mealBO);
        }

        return mealsList;
    }

    public Recipe convertForDataBaseFromAPI(MealBO randomMeal){
        Recipe recipe=new Recipe();

        recipe.setRecipeTitle(randomMeal.getMealName());
        recipe.setRecipeImage(randomMeal.getImage());
        recipe.setRecipeDescription(randomMeal.getInstructions());
        recipe.setRecipeCategory(randomMeal.getCategory());
        recipe.setRecipeArea(randomMeal.getArea());
        recipe.setRecipeMode("FromAPI");

        return recipe;
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
}