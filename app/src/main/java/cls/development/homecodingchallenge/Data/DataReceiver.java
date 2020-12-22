package cls.development.homecodingchallenge.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cls.development.homecodingchallenge.Fragments.DrinksFragment;
import cls.development.homecodingchallenge.Fragments.MoreInfoFragment;

public class DataReceiver {
    private static final String URI_LOOKUP = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=";
    private static final String URI_ALL_COCKTAILS = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail";
    private static final String TAG = "DataReceiver";
    private static final String DRINKS_CONSTANT = "drinks";
    private static final String CONSTANT_NAME_DRINK = "strDrink";
    private static final String CONSTANT_IMAGE_DRINK = "strDrinkThumb";
    private static final String CONSTANT_ID_DRINK = "idDrink";
    private static final String CONSTANT_CATEGORY_DRINK = "strCategory";
    private static final String CONSTANT_INSTRUCTIONS_DRINK = "strInstructions";
    private static final String CONSTANT_ALCOHOLIC_DRINK = "strAlcoholic";
    private static final String CONSTANT_MEASURE_DRINK = "strMeasure";
    private static final String CONSTANT_INGREDIENT_DRINK = "strIngredient";

    private final Context context;
    @SuppressLint("StaticFieldLeak")
    public static DataReceiver dataReceiver;
    private static final int indexMoreInfo =0;
    private RequestQueue requestQueue;

    //Singleton
    public static DataReceiver getInstance(Context context){
        if(dataReceiver==null)
            dataReceiver = new DataReceiver(context);
        return dataReceiver;
    }

    public DataReceiver(Context context) {
        this.context = context;
    }
    public void initFromId(String id, MoreInfoFragment moreInfoFragment){
        StringRequest request = new StringRequest(URI_LOOKUP +id,
                responseValue -> getJSONDataFromId(moreInfoFragment,responseValue), error -> {

                });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }

    public void initAllDrinks(DrinksFragment allDrinksFragment) {
        StringRequest request = new StringRequest(URI_ALL_COCKTAILS,
                responseValue -> {
                    getJSONDataSimpleAllOrdinaryDrinks(allDrinksFragment,responseValue);
                }, error -> Log.d(TAG, "Error All Cocktails " +error));
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void getJSONDataSimpleAllOrdinaryDrinks(DrinksFragment allDrinksFragment, String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray drinksArray = object.getJSONArray(DRINKS_CONSTANT);
            ArrayList<Drink> al = new ArrayList<>();


            for(int i = 0; i < drinksArray.length(); ++i) {
                Drink drink = new Drink(drinksArray.getJSONObject(i).getString(CONSTANT_NAME_DRINK),
                        drinksArray.getJSONObject(i).getString(CONSTANT_IMAGE_DRINK),
                        drinksArray.getJSONObject(i).getString(CONSTANT_ID_DRINK));
                al.add(drink);
            }
            allDrinksFragment.dataCollected(al);


        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    public void getJSONDataFromId(MoreInfoFragment moreInfoFragment, String responseValue) {
        try {
            JSONObject object = new JSONObject(responseValue);
            JSONArray drinksArray = object.getJSONArray(DRINKS_CONSTANT);
            ArrayMap<String, String> ingredients = getIngredientArrayMap(drinksArray);

            Drink drink = new Drink(drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_NAME_DRINK),
                    drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_IMAGE_DRINK),
                    drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_ID_DRINK),
                    drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_CATEGORY_DRINK),
                    drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_INSTRUCTIONS_DRINK),
                    drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_ALCOHOLIC_DRINK),
                    ingredients
            );

            ArrayList<Drink> arrayList = new ArrayList<>();
            arrayList.add(drink);
            moreInfoFragment.dataCollected(arrayList);


        } catch (JSONException e) {
            e.printStackTrace();

        }    }

    private ArrayMap<String, String> getIngredientArrayMap(JSONArray drinksArray) {
        ArrayMap<String,String> ingredients = new ArrayMap<>();
        for(int i = 1;i< 15;i++ ){
            String valueIngredient = "";
            String valueAmount = "";

            try {
                valueIngredient = drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_INGREDIENT_DRINK + i);
                if(valueIngredient.equals("null")|| valueIngredient.equals("")){
                    break;
                }
            }
            catch (Exception e){
                break;

            }
            try {
                valueAmount = drinksArray.getJSONObject(indexMoreInfo).getString(CONSTANT_MEASURE_DRINK + i);
                if(valueAmount.equals("null") || valueAmount.equals(""))
                    valueAmount = "/";

            }catch (Exception e){
                Log.e("ExceptionParsingJson", "getJSONDataFromId: ",e );

            }
            ingredients.put(valueIngredient,valueAmount);


        }
        return ingredients;
    }
}
