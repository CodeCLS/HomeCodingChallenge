package cls.development.homecodingchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import cls.development.homecodingchallenge.Fragments.DrinksFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToFragment(new DrinksFragment());
    }


    void goToFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}