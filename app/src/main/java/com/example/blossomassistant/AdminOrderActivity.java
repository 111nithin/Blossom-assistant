package com.example.blossomassistant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("View Orders");
        }

        TextView empty = findViewById(R.id.txtOrdersEmpty);
        RecyclerView recycler = findViewById(R.id.recyclerAdminOrders);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<OrderModel> orders = OrderManager.getOrders();
        if (orders.isEmpty()) {
            empty.setText("No orders yet");
            empty.setVisibility(TextView.VISIBLE);
        } else {
            empty.setVisibility(TextView.GONE);
        }

        recycler.setAdapter(new AdminOrderAdapter(orders));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

