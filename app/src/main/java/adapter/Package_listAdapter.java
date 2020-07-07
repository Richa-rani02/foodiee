package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.package_model;
import welcome.in.foodiee.R;

public class Package_listAdapter extends RecyclerView.Adapter<Package_listAdapter.ViewHolder> {
    private Context context;
    public ArrayList<package_model> packdata;

    public Package_listAdapter(ArrayList<package_model> packdata) {
        this.packdata = packdata;
    }

    @Override
    public Package_listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View Packitem = layoutInflater.inflate(R.layout.row_package_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(Packitem);
    }

    @Override
    public void onBindViewHolder(@NonNull Package_listAdapter.ViewHolder holder, int position) {
        package_model data = packdata.get(position);
        holder.pack_name.setText(data.getPackName());
        holder.pack_description.setText(data.getPackDesc());
        holder.pack_price.setText(data.getPackPrice());
        Picasso.with(context).load(data.getPackImg())
                .into(holder.pack_img);
    }

    @Override
    public int getItemCount() {
        return packdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pack_img;
        public TextView pack_name, pack_price, pack_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pack_name = itemView.findViewById(R.id.pack_name);
            pack_img = itemView.findViewById(R.id.pack_img);
            pack_description = itemView.findViewById(R.id.pack_description);
            pack_price = itemView.findViewById(R.id.pack_price);
        }
    }
}
