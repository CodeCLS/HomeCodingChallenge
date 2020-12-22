package cls.development.homecodingchallenge;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataReceiver {
    private static String responseAllOrdinaryDrinks;
    private Context context;
    public static DataReceiver dataReceiver;
    private static String responseFromId;
    private static final int c=0;
    private RequestQueue requestQueue;


    public static DataReceiver getInstance(Context context){
        if(dataReceiver==null)
            dataReceiver = new DataReceiver(context);
        return dataReceiver;
    }
    public DataReceiver(Context context) {
        this.context = context;
        init();
    }

    private void  init() {



    }
    public void initFromId(String id, MoreInfoFragment moreInfoFragment){
        StringRequest request = new StringRequest(" https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" +id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseValue) {
                        getJSONDataFromId(moreInfoFragment,responseValue);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }

    public void initAllDrinks(DrinksFragment allDrinksFragment) {

        StringRequest request = new StringRequest("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseValue) {
                        Log.d("TAG", "getJSONDataSimpleAllOrdinaryDrinks:12 " + responseValue);
                        getJSONDataSimpleAllOrdinaryDrinks(allDrinksFragment,responseValue);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "getJSONDataSimpleAllOrdinaryDrinks:14 " +error);


            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void getJSONDataSimpleAllOrdinaryDrinks(DrinksFragment allDrinksFragment, String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray drinksArray = object.getJSONArray("drinks");
            ArrayList<Drink> al = new ArrayList<>();


            for(int i = 0; i < drinksArray.length(); ++i) {
                Drink drink = new Drink(drinksArray.getJSONObject(i).getString("strDrink"),
                        drinksArray.getJSONObject(i).getString("strDrinkThumb"),
                        drinksArray.getJSONObject(i).getString("idDrink"));
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
            JSONArray drinksArray = object.getJSONArray("drinks");
            HashMap<String, String> ingredients = getIngredientHashMap(drinksArray);
            List<String> tags = Arrays.asList(drinksArray.getJSONObject(c).getString("strTags").split(","));
            Drink drink = new Drink(drinksArray.getJSONObject(c).getString("strDrink"),
                    drinksArray.getJSONObject(c).getString("strDrinkThumb"),
                    drinksArray.getJSONObject(c).getString("idDrink"),
                    drinksArray.getJSONObject(c).getString("strCategory"),
                    drinksArray.getJSONObject(c).getString("strInstructions"),
                    drinksArray.getJSONObject(c).getString("strAlcoholic"),
                    tags,
                    ingredients
            );

            ArrayList<Drink> arrayList = new ArrayList<>();
            arrayList.add(drink);
            moreInfoFragment.dataCollected(arrayList);


        } catch (JSONException e) {
            e.printStackTrace();

        }    }

    private HashMap<String, String> getIngredientHashMap(JSONArray drinksArray) {
        HashMap<String,String> ingredients = new HashMap<>();
        for(int i = 0;i< 15;i++ ){
            String valueIngredient = "";
            String valueAmount = "";

            try {
                valueIngredient = drinksArray.getJSONObject(c).getString("strIngredient" + i);
            }
            catch (Exception e){
                break;

            }
            try {
                valueAmount = drinksArray.getJSONObject(c).getString("strMeasure" + i);
            }catch (Exception e){
                Log.e("ExceptionParsingJson", "getJSONDataFromId: ",e );

            }
            ingredients.put(valueIngredient,valueAmount);


        }
        return ingredients;
    }
}
