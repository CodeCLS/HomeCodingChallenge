package cls.development.homecodingchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToFragment(new DrinksFragment());
    }

    void goToFragment(Fragment fragment){

        try {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragment instanceof MoreInfoFragment)
                fragmentTransaction.addToBackStack("Info");

            fragmentTransaction.replace(R.id.frameMain, fragment);
            fragmentTransaction.commit();
        }
        catch (Exception e){
            Log.e(TAG, "goToFragment: ",e );
        }

    }
}