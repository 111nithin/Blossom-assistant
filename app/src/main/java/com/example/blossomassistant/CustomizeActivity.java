package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomizeActivity extends AppCompatActivity {

    private ImageView preview;
    private Spinner spinner;
    private TextView designNameText;
    private TextView priceText;
    private Button btnCart;
    private Button btnBook;
    private Button btnViewCart;

    private int basePrice;
    private String designName;
    private int selectedImage;
    private String selectedCustomization = "Standard";
    private int finalPrice;
    private final int[] imageOptions = {
            R.drawable.big_rose_wedding,
            R.drawable.birthday_theme,
            R.drawable.default_decor,
            R.drawable.engagement_decor,
            R.drawable.housewarming_decor,
            R.drawable.marigold_birthday,
            R.drawable.orchid_wedding,
            R.drawable.small_rose_wedding
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Customize Design");
        }

        preview = findViewById(R.id.preview);
        spinner = findViewById(R.id.spinnerFlower);
        designNameText = findViewById(R.id.txtDesignName);
        priceText = findViewById(R.id.txtPrice);
        btnCart = findViewById(R.id.btnAddCart);
        btnBook = findViewById(R.id.btnBookNow);
        btnViewCart = findViewById(R.id.btnViewCart);

        basePrice = getIntent().getIntExtra("price", 0);
        selectedImage = getIntent().getIntExtra("image", R.drawable.default_decor);
        designName = getIntent().getStringExtra("designName");
        if (designName == null || designName.trim().isEmpty()) {
            designName = "Custom Design";
        }
        designNameText.setText("Design: " + designName);
        preview.setImageResource(selectedImage);

        String[] customizations = {
                "Standard",
                "Premium Flowers (+₹200)",
                "Lighting Upgrade (+₹400)",
                "Full Premium Setup (+₹600)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                customizations
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedCustomization = customizations[pos];
                finalPrice = basePrice + (pos * 200);
                priceText.setText("₹" + finalPrice);
                selectedImage = imageOptions[pos % imageOptions.length];
                preview.setImageResource(selectedImage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnCart.setOnClickListener(v -> {
            String item = "Design: " + designName
                    + " | Customization: " + selectedCustomization
                    + " | Price: ₹" + finalPrice;

            boolean added = CartManager.addItem(item);
            String message = added ? "Added to Cart" : "Item already in Cart";
            Toast.makeText(CustomizeActivity.this, message, Toast.LENGTH_SHORT).show();
        });

        btnBook.setOnClickListener(v -> {
            Intent i = new Intent(CustomizeActivity.this, OrderActivity.class);
            i.putExtra("price", "₹" + finalPrice);
            i.putExtra("designName", designName);
            i.putExtra("customization", selectedCustomization);
            startActivity(i);
        });

        btnViewCart.setOnClickListener(v ->
                startActivity(new Intent(CustomizeActivity.this, CartActivity.class)));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}