package cls.development.homecodingchallenge;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Drink {
    private String name;
    private String imageUri;
    private String id;
    private String category;
    private String isAlcohol;
    private String instructions;
    private ArrayMap<String,String> ingredients;


    public Drink(String name, String imageUri, String id, String category,String isAlcohol, String instructions,  ArrayMap<String,String> ingredients) {
        this.name = name;
        this.imageUri = imageUri;
        this.id = id;
        this.category = category;
        this.instructions = instructions;
        this.isAlcohol = isAlcohol;
        this.ingredients = ingredients;
    }

    public Drink(String name, String imageUri, String id) {
        this.name = name;
        this.imageUri = imageUri;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIsAlcohol() {
        return isAlcohol;
    }

    public void setIsAlcohol(String isAlcohol) {
        this.isAlcohol = isAlcohol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }



    public ArrayMap<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayMap<String, String> ingredients) {
        this.ingredients = ingredients;
    }
}
