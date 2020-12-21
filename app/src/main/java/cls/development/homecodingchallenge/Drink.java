package cls.development.homecodingchallenge;

import java.util.ArrayList;

public class Drink {
    private String name;
    private String imageUri;
    private String id;
    private String description;
    private String category;
    private boolean isAlcohol;
    private String instructions;
    private ArrayList<String> tags;
    private ArrayList<String> ingredients;

    public Drink(String name, String imageUri, String id, String description, String category, boolean isAlcohol, String instructions, ArrayList<String> tags, ArrayList<String> ingredients) {
        this.name = name;
        this.imageUri = imageUri;
        this.id = id;
        this.description = description;
        this.category = category;
        this.isAlcohol = isAlcohol;
        this.instructions = instructions;
        this.tags = tags;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAlcohol() {
        return isAlcohol;
    }

    public void setAlcohol(boolean alcohol) {
        isAlcohol = alcohol;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
