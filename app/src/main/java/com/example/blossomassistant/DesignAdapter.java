package com.example.blossomassistant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {

    private final Context context;
    private final List<DesignModel> list;
    private final String category;

    public DesignAdapter(Context context, List<DesignModel> list, String category) {
        this.context = context;
        this.list = list;
        this.category = category;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DesignModel model = list.get(position);

        holder.name.setText(model.getName());
        holder.price.setText("₹" + model.getPrice());
        holder.image.setImageResource(model.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, CustomizeActivity.class);
            i.putExtra("price", model.getPrice());
            i.putExtra("image", model.getImage());
            i.putExtra("designName", model.getName());
            i.putExtra("category", category);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.txtName);
            price = v.findViewById(R.id.txtPrice);
            image = v.findViewById(R.id.imgDesign);
        }
    }
}