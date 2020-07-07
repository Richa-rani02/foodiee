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

import java.security.PublicKey;
import java.util.ArrayList;

import model.Addon_model;
import welcome.in.foodiee.R;

public class Addon_listAdapter extends RecyclerView.Adapter<Addon_listAdapter.ViewHolder> {
    private Context context;
    public ArrayList<Addon_model> Addondata;

    public Addon_listAdapter(ArrayList<Addon_model> addondata) {
        Addondata = addondata;
    }

    @Override
    public Addon_listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View Addonitem = layoutInflater.inflate(R.layout.row_addon_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(Addonitem);
    }

    @Override
    public void onBindViewHolder(@NonNull Addon_listAdapter.ViewHolder holder, int position) {
        Addon_model addondata = Addondata.get(position);
        holder.extra_name.setText(addondata.getExtraName());
        holder.extra_price.setText(addondata.getExtraPrice());
        holder.extra_desc.setText(addondata.getExtraDesc());
        Picasso.with(context).load(addondata.getExtraImg()).into(holder.extra_img);
    }

    @Override
    public int getItemCount() {
        return Addondata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView extra_img;
        public TextView extra_name, extra_desc, extra_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            extra_name = itemView.findViewById(R.id.extra_name);
            extra_desc = itemView.findViewById(R.id.extra_description);
            extra_price = itemView.findViewById(R.id.extra_price);
            extra_img = itemView.findViewById(R.id.extra_img);
        }
    }
}
