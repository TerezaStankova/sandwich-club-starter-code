package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //Parse data from Json
    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObjectSandwich = new JSONObject(json);
            JSONObject jsonObjectName = jsonObjectSandwich.getJSONObject("name");

            String mainName = jsonObjectName.getString("mainName");
            JSONArray alsoKnownAsJSON = jsonObjectName.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();

            if (alsoKnownAsJSON.length() == 0) {
                alsoKnownAs.add("No nickname");
            }
            else {
                for (int i = 0; i < alsoKnownAsJSON.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsJSON.getString(i));
                }
            }

            String placeOfOrigin = jsonObjectSandwich.getString("placeOfOrigin");
            if (placeOfOrigin.length() == 0){
                placeOfOrigin = "Unknown";
            }
            String description = jsonObjectSandwich.getString("description");
            String image = jsonObjectSandwich.getString("image");
            JSONArray ingredientsJSON = jsonObjectSandwich.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            if (ingredientsJSON.length() == 0) {
                ingredients.add("Ingredients not available");
            }

            else {
                for (int i = 0; i < ingredientsJSON.length(); i++) {
                    ingredients.add((i+1) + ". " + ingredientsJSON.getString(i) + "!");
                }
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }

        //In case data not parsed return null
        catch(JSONException ex) {
            return null;
        }
    }
}
