package cls.development.homecodingchallenge;

import android.content.Context;
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
    private View.OnClickListener listener;
    private ArrayList<Drink> drinkArrayList = new ArrayList<Drink>();
    private Context context;
    private static String idCurrentItem;

    public AdapterAllDrinks(ArrayList<Drink> drinkArrayList, View.OnClickListener listener) {
        this.drinkArrayList = drinkArrayList;
        this.listener = listener;
    }

    public AdapterAllDrinks(ArrayList<Drink> drinkArrayList) {
        this.drinkArrayList = drinkArrayList;
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
        idCurrentItem = drinkArrayList.get(position).getId();
        viewHolder.container.setOnClickListener(listener);




    }
    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }
    public String getIdCurrentItem(){
        return idCurrentItem;
    }

    @Override
    public int getItemCount() {
        return drinkArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
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
