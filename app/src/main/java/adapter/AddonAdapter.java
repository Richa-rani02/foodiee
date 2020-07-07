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

import welcome.in.foodiee.R;
import model.Addon_model;

public class AddonAdapter extends RecyclerView.Adapter<AddonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Addon_model>addondata;

    public AddonAdapter(ArrayList<Addon_model> addondata) {
        this.addondata = addondata;
    }

    @Override
    public AddonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View addonitem=layoutInflater.inflate(R.layout.row_addon,parent,false);
        context = parent.getContext();
        return new ViewHolder(addonitem);
    }

    @Override
    public void onBindViewHolder(@NonNull AddonAdapter.ViewHolder holder, int position) {
      Addon_model addon_item=addondata.get(position);
      holder.title.setText(addon_item.getExtraName());
        Picasso.with(context).load(addon_item.getExtraImg())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return addondata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.addon_title);
            image=itemView.findViewById(R.id.addon_img);

        }
    }
}
