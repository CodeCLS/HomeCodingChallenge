package cls.development.homecodingchallenge;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoreInfoFragment extends Fragment implements DataCollected {
    private String id;
    private TextView name;
    private ImageView image;
    private TextView category;
    private TextView isAlcohol;
    private TextView instructions;
    private LinearLayout tags;
    private LinearLayout ingredients;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.more_info_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        try {
            name = getView().findViewById(R.id.nameMoreInfo);
            image = getView().findViewById(R.id.imageMoreInfo);
            category = getView().findViewById(R.id.categoryMoreInfo);
            instructions = getView().findViewById(R.id.instructionsMoreInfo);
            isAlcohol = getView().findViewById(R.id.alcoholMoreInfo);
            tags = getView().findViewById(R.id.tagsMoreInfo);
            ingredients = getView().findViewById(R.id.ingredientsMoreInfo);
        }
        catch (Exception e){
            Log.d("aiosd", "init: " +e);
        }
        fetchData();


    }
    private void fetchData(){
        DataReceiver.getInstance(getContext()).initFromId(id,this);
    }

    public void setId(String idCurrentItem) {
        this.id = idCurrentItem;
    }

    @Override
    public void dataCollected(ArrayList<Drink> array) {
            Drink drink = array.get(0);
            name.setText(drink.getName());
            Picasso.get().load(drink.getImageUri()).into(image);
            category.setText(drink.getCategory());
            isAlcohol.setText(drink.getIsAlcohol());
            instructions.setText(drink.getInstructions());


    }
}
