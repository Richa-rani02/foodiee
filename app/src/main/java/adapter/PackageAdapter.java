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
import model.package_model;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {
    private Context context;
    public ArrayList<package_model> packdata;

    public PackageAdapter(ArrayList<package_model> packdata) {
        this.packdata = packdata;
    }

    @NonNull
    @Override
    public PackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View packitem = layoutInflater.inflate(R.layout.row_packages, parent, false);

        context = parent.getContext();

        return new ViewHolder(packitem);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.ViewHolder holder, int position) {
        package_model data = packdata.get(position);
        holder.title.setText(data.getPackName());
        Picasso.with(context).load(data.getPackImg())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return packdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pack_title);
            image = itemView.findViewById(R.id.pack_img);

        }
    }
}
