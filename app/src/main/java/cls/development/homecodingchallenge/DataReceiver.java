package cls.development.homecodingchallenge;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataReceiver {
    private static String responseAllOrdinaryDrinks;
    private Context context;
    public static DataReceiver dataReceiver;
    private static String responseFromId;

    public static DataReceiver getInstance(){
        if(dataReceiver==null)
            dataReceiver = new DataReceiver();
        return dataReceiver;
    }
    public DataReceiver() {
        init();
    }

    private void  init() {
        initAllDrinks();

    }
    private void initFromId(String id){
        StringRequest request = new StringRequest(" https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" +id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseValue) {
                        responseFromId = responseValue;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(request);

    }

    private void initAllDrinks() {
        StringRequest request = new StringRequest("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseValue) {
                        responseAllOrdinaryDrinks = responseValue;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(request);
    }

    public ArrayList<Drink> getJSONDataSimpleAllOrdinaryDrinks() {
        try {
            JSONObject object = new JSONObject(responseAllOrdinaryDrinks);
            JSONArray drinksArray = object.getJSONArray("drinks");
            ArrayList<Drink> al = new ArrayList<>();
            for(int i = 0; i < drinksArray.length(); ++i) {
                Drink drink = new Drink(drinksArray.getJSONObject(i).getString("strDrink"),
                        drinksArray.getJSONObject(i).getString("strDrinkThumb"),
                        drinksArray.getJSONObject(i).getString("idDrink"));
                al.add(drink);
            }
            return al;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }

    }

    public Drink getJSONDataFromId() {
        try {
            JSONObject object = new JSONObject(responseAllOrdinaryDrinks);
            JSONArray drinksArray = object.getJSONArray("drinks");
            ArrayList<Drink> al = new ArrayList<>();
            int i=0;
            Drink drink = new Drink(drinksArray.getJSONObject(i).getString("strDrink"),
                    drinksArray.getJSONObject(i).getString("strDrinkThumb"),
                    drinksArray.getJSONObject(i).getString("idDrink"),
                    drinksArray.getJSONObject(i).getString("idDrink"));
            al.add(drink);

            return drink;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }    }
}
