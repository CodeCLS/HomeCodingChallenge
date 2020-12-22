package cls.development.homecodingchallenge.Fragments;

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
import androidx.transition.Transition;

import java.util.ArrayList;
import java.util.Objects;

import cls.development.homecodingchallenge.ItemWork.AdapterAllDrinks;
import cls.development.homecodingchallenge.Interfaces.DataCollected;
import cls.development.homecodingchallenge.Data.DataReceiver;
import cls.development.homecodingchallenge.Data.Drink;
import cls.development.homecodingchallenge.ItemWork.ImageTransition;
import cls.development.homecodingchallenge.Interfaces.ItemClicked;
import cls.development.homecodingchallenge.R;

public class DrinksFragment extends Fragment implements DataCollected, ItemClicked {
    private static final String CONSTANT_TRANSITION_NAME = "image_more_transition";
    private static final String CONSTANT_BACKSTACK_MORE_INFO = "Info";
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.all_drinks_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerview);
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
        transitionToFragment(id, imageView);
    }

    private void transitionToFragment(String id, ImageView imageView) {
        MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
        moreInfoFragment.setId(id);
        Transition transition = new ImageTransition();
        moreInfoFragment.setSharedElementEnterTransition(transition);
        moreInfoFragment.setSharedElementReturnTransition(transition);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
        ViewCompat.setTransitionName(imageView, CONSTANT_TRANSITION_NAME);
        fragmentTransaction.addSharedElement(imageView, CONSTANT_TRANSITION_NAME);
        fragmentTransaction.addToBackStack(CONSTANT_BACKSTACK_MORE_INFO);
        fragmentTransaction.replace(R.id.frameMain, moreInfoFragment);
        fragmentTransaction.commit();
    }
}
