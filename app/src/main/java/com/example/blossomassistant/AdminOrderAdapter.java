package com.example.blossomassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.VH> {

    private final List<OrderModel> orders;

    public AdminOrderAdapter(List<OrderModel> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_order, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        OrderModel o = orders.get(position);
        h.txtCustomer.setText("Name: " + o.getName());
        h.txtPhone.setText("Phone: " + o.getPhone());
        h.txtAddress.setText("Address: " + o.getAddress());
        h.txtDateTime.setText("Date & Time: " + o.getDate() + " " + o.getTime());
        h.txtDesign.setText("Design: " + o.getDesignName());
        h.txtPrice.setText("Price: " + o.getPrice());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtCustomer;
        TextView txtPhone;
        TextView txtAddress;
        TextView txtDateTime;
        TextView txtDesign;
        TextView txtPrice;

        VH(@NonNull View itemView) {
            super(itemView);
            txtCustomer = itemView.findViewById(R.id.txtOrderCustomer);
            txtPhone = itemView.findViewById(R.id.txtOrderPhone);
            txtAddress = itemView.findViewById(R.id.txtOrderAddress);
            txtDateTime = itemView.findViewById(R.id.txtOrderDateTime);
            txtDesign = itemView.findViewById(R.id.txtOrderDesign);
            txtPrice = itemView.findViewById(R.id.txtOrderPrice);
        }
    }
}

