package com.Ricepify.Service;


import org.springframework.stereotype.Service;
import com.Ricepify.Models.RandomMealEntity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealsService {
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/random.php";
    public RandomMealEntity getRandomMealInfo() {

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
                RandomMealEntity randomMealEntity = new RandomMealEntity();
                randomMealEntity.setMealName(mealObject.get("strMeal").getAsString());
                randomMealEntity.setCategory(mealObject.get("strCategory").getAsString());
                randomMealEntity.setVideo(mealObject.get("strYoutube").getAsString());
                randomMealEntity.setInstructions(mealObject.get("strInstructions").getAsString());
                randomMealEntity.setImage(mealObject.get("strMealThumb").getAsString());
                randomMealEntity.setArea(mealObject.get("strArea").getAsString());
                randomMealEntity.setId(mealObject.get("idMeal").getAsString());


                return randomMealEntity;

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
    public List<RandomMealEntity> getRandomMeals(int numberOfMeals) {
        List<RandomMealEntity> mealsList = new ArrayList<>();

        for (int i = 0; i < numberOfMeals; i++) {
            RandomMealEntity randomMealEntity = getRandomMealInfo();

            mealsList.add(randomMealEntity);

        }

        return mealsList;
    }

}