package com.example.blossomassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminDesignAdapter extends RecyclerView.Adapter<AdminDesignAdapter.VH> {

    public interface DesignActionListener {
        void onDelete(int position);
        void onStartEdit(int position);
        void onCancelEdit();
    }

    private final List<DesignModel> list;
    private final DesignActionListener listener;

    public AdminDesignAdapter(List<DesignModel> list, DesignActionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_design, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        DesignModel m = list.get(position);
        h.txtName.setText(m.getName());
        h.txtPrice.setText("₹" + m.getPrice());
        h.txtCategory.setText(m.getCategory());
        h.image.setImageResource(m.getImage());

        h.btnEdit.setOnClickListener(v -> listener.onStartEdit(h.getBindingAdapterPosition()));
        h.btnDelete.setOnClickListener(v -> listener.onDelete(h.getBindingAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtName;
        TextView txtPrice;
        TextView txtCategory;
        Button btnEdit;
        Button btnDelete;

        VH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgAdminDesign);
            txtName = itemView.findViewById(R.id.txtAdminDesignName);
            txtPrice = itemView.findViewById(R.id.txtAdminDesignPrice);
            txtCategory = itemView.findViewById(R.id.txtAdminDesignCategory);
            btnEdit = itemView.findViewById(R.id.btnAdminDesignEdit);
            btnDelete = itemView.findViewById(R.id.btnAdminDesignDelete);
        }
    }
}

