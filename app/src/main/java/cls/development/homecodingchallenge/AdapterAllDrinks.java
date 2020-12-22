package cls.development.homecodingchallenge;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAllDrinks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DrinksFragment allDrinksFragment;
    private ArrayList<Drink> drinkArrayList = new ArrayList<Drink>();
    private Context context;
    private String idCurrentItem;

    public AdapterAllDrinks(ArrayList<Drink> drinkArrayList, DrinksFragment allDrinksFragment) {
        this.drinkArrayList = drinkArrayList;
        this.allDrinksFragment = allDrinksFragment;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(drinkArrayList.get(position).getName());
        Picasso.get().load(drinkArrayList.get(position).getImageUri()).into(viewHolder.imageView);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCurrentItem = drinkArrayList.get(position).getId();
                Log.d("AdapterClick", "onClick12: " + idCurrentItem);
                allDrinksFragment.itemClicked(idCurrentItem,viewHolder.imageView);
            }
        });




    }



    @Override
    public int getItemCount() {
        return drinkArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView imageView;
        TextView textView;
        LinearLayout container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.item_container);
            imageView = itemView.findViewById(R.id.drink_item_image);
            textView = itemView.findViewById(R.id.drink_item_text);
        }


    }
}
