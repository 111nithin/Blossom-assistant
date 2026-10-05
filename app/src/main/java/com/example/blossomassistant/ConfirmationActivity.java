package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ConfirmationActivity extends AppCompatActivity {

    private boolean orderSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Booking Confirmed");
        }

        TextView txt = findViewById(R.id.confirmText);
        Button btnGoCart = findViewById(R.id.btnGoCart);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String designName = getIntent().getStringExtra("designName");
        String price = getIntent().getStringExtra("price");
        boolean fromCart = getIntent().getBooleanExtra("fromCart", false);

        if (savedInstanceState != null) {
            orderSaved = savedInstanceState.getBoolean("orderSaved", false);
        }

        if (!orderSaved) {
            OrderManager.addOrder(new OrderModel(
                    valueOrPlaceholder(name),
                    valueOrPlaceholder(phone),
                    valueOrPlaceholder(address),
                    valueOrPlaceholder(date),
                    valueOrPlaceholder(time),
                    valueOrPlaceholder(designName),
                    valueOrPlaceholder(price)
            ));
            orderSaved = true;
        }

        String heading = fromCart ? "Items Booked from Cart" : "Order Confirmed";

        txt.setText(heading + "\n\n"
                + "Name: " + valueOrPlaceholder(name) + "\n"
                + "Phone: " + valueOrPlaceholder(phone) + "\n"
                + "Address: " + valueOrPlaceholder(address) + "\n"
                + "Design: " + valueOrPlaceholder(designName) + "\n"
                + "Total Price: " + valueOrPlaceholder(price) + "\n"
                + "Date: " + valueOrPlaceholder(date) + "\n"
                + "Time: " + valueOrPlaceholder(time));

        if (fromCart) {
            CartManager.clearCart();
        }

        btnGoCart.setOnClickListener(v ->
                startActivity(new Intent(ConfirmationActivity.this, CartActivity.class)));
        btnBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("orderSaved", orderSaved);
    }

    private String valueOrPlaceholder(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "N/A";
        }
        return value;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}