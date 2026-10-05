package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button btnClearCart;
    private Button btnBookAll;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Your Cart");
        }

        listView = findViewById(R.id.cartList);
        btnClearCart = findViewById(R.id.btnClearCart);
        btnBookAll = findViewById(R.id.btnBookAll);
        txtTotal = findViewById(R.id.txtTotal);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                CartManager.cartItems
        );
        listView.setAdapter(adapter);
        updateCartSummary();

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            CartManager.removeItem(position);
            adapter.notifyDataSetChanged();
            updateCartSummary();
            Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
            return true;
        });

        btnClearCart.setOnClickListener(v -> {
            CartManager.clearCart();
            adapter.notifyDataSetChanged();
            updateCartSummary();
            Toast.makeText(this, "Cart cleared", Toast.LENGTH_SHORT).show();
        });

        btnBookAll.setOnClickListener(v -> {
            if (CartManager.getItems().isEmpty()) {
                Toast.makeText(this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                return;
            }

            int total = calculateTotalPrice(CartManager.getItems());
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            intent.putExtra("price", "₹" + total);
            intent.putExtra("fromCart", true);
            intent.putStringArrayListExtra("cartItems", new ArrayList<>(CartManager.cartItems));
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        updateCartSummary();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void updateCartSummary() {
        List<String> items = CartManager.getItems();
        if (items.isEmpty()) {
            txtTotal.setText("Cart is Empty");
            btnBookAll.setEnabled(false);
            return;
        }
        int total = calculateTotalPrice(items);
        txtTotal.setText("Total: ₹" + total);
        btnBookAll.setEnabled(true);
    }

    private int calculateTotalPrice(List<String> items) {
        int total = 0;
        for (String item : items) {
            total += extractPrice(item);
        }
        return total;
    }

    private int extractPrice(String item) {
        if (item == null) {
            return 0;
        }
        Pattern pattern = Pattern.compile("Price:\\s*₹(\\d+)");
        Matcher matcher = pattern.matcher(item);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
}