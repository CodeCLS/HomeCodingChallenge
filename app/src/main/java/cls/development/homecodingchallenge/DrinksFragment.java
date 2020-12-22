package cls.development.homecodingchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinksFragment extends Fragment implements DataCollected,ItemClicked{
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.all_drinks_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerview);
        DataReceiver.getInstance(getContext()).initAllDrinks(this);
    }
    void setAdapter(ArrayList<Drink> drink){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterAllDrinks adapter =new AdapterAllDrinks(drink,this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void dataCollected(ArrayList<Drink> array) {
        setAdapter(array);


    }

    @Override
    public void itemClicked(String id) {
        MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
        moreInfoFragment.setId(id);
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.goToFragment(moreInfoFragment);
        }
    }
}
