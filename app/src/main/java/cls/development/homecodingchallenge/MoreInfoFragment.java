package cls.development.homecodingchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MoreInfoFragment extends Fragment {
    private static String id;
    private TextView name;
    private TextView image;
    private TextView description;
    private TextView category;
    private TextView isAlcohol;
    private TextView instructions;
    private LinearLayout tags;
    private LinearLayout ingredients;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.more_info_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        name = getView().findViewById(R.id.nameMoreInfo);
        image = getView().findViewById(R.id.imageMoreInfo);
        description = getView().findViewById(R.id.descriptionMoreInfo);
        category = getView().findViewById(R.id.categoryMoreInfo);
        instructions = getView().findViewById(R.id.instructionsMoreInfo);
        isAlcohol = getView().findViewById(R.id.alcoholMoreInfo);
        tags = getView().findViewById(R.id.tagsMoreInfo);
        ingredients = getView().findViewById(R.id.ingredientsMoreInfo);


    }
    private void assign(){
        DataReceiver dataReceiver = DataReceiver.getInstance().getJSONDataFromId();
        name.setText();
    }

    public void setId(String idCurrentItem) {
        this.id = idCurrentItem;
    }
}
