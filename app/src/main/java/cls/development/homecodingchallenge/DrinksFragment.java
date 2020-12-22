package cls.development.homecodingchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;

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
    public void itemClicked(String id, ImageView imageView) {
        MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
        moreInfoFragment.setId(id);
        Transition transition = new ImageTransition();
        moreInfoFragment.setSharedElementEnterTransition(transition);
        moreInfoFragment.setSharedElementReturnTransition(transition);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ViewCompat.setTransitionName(imageView, "image_more_transition");
        fragmentTransaction.addSharedElement(imageView, "image_more_transition");
        fragmentTransaction.addToBackStack("Info");
        fragmentTransaction.replace(R.id.frameMain, moreInfoFragment);
        fragmentTransaction.commit();
    }
}
