package cls.development.homecodingchallenge.ItemWork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cls.development.homecodingchallenge.Data.Drink;
import cls.development.homecodingchallenge.Fragments.DrinksFragment;
import cls.development.homecodingchallenge.R;

public class AdapterAllDrinks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  final DrinksFragment allDrinksFragment;
    private final ArrayList<Drink> drinkArrayList;
    private String idCurrentItem;

    public AdapterAllDrinks(ArrayList<Drink> drinkArrayList, DrinksFragment allDrinksFragment) {
        this.drinkArrayList = drinkArrayList;
        this.allDrinksFragment = allDrinksFragment;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(drinkArrayList.get(position).getName());
        Picasso.get().load(drinkArrayList.get(position).getImageUri()).into(viewHolder.imageView);
        viewHolder.container.setOnClickListener(v -> {
            idCurrentItem = drinkArrayList.get(position).getId();
            allDrinksFragment.itemClicked(idCurrentItem,viewHolder.imageView);
        });




    }



    @Override
    public int getItemCount() {
        return drinkArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
