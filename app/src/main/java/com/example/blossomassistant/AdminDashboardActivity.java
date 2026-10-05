package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Admin Dashboard");
        }

        Button btnManageDesigns = findViewById(R.id.btnManageDesigns);
        Button btnViewOrders = findViewById(R.id.btnViewOrders);
        Button btnManageCart = findViewById(R.id.btnManageCart);
        Button btnLogout = findViewById(R.id.btnAdminLogout);

        btnManageDesigns.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, AdminDesignActivity.class)));

        btnViewOrders.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, AdminOrderActivity.class)));

        btnManageCart.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, CartActivity.class)));

        btnLogout.setOnClickListener(v -> {
            Intent i = new Intent(AdminDashboardActivity.this, AdminLoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

