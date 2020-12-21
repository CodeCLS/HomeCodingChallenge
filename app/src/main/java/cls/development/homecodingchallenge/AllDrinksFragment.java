package cls.development.homecodingchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllDrinksFragment extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.all_drinks_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerview);
        setAdapter(DataReceiver.getInstance().getJSONDataSimpleAllOrdinaryDrinks());
    }
    void setAdapter(ArrayList<Drink> drink){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterAllDrinks adapter =new AdapterAllDrinks(drink);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
                moreInfoFragment.setId(adapter.getIdCurrentItem());
                fragmentTransaction.replace(R.id.frameMain,moreInfoFragment);
                fragmentTransaction.commit();
            }
        });

        recyclerView.setAdapter(adapter);


    }
}
