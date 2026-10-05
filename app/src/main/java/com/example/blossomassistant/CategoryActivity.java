package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    private Button wedding;
    private Button birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Choose Category");
        }

        wedding = findViewById(R.id.btnWedding);
        birthday = findViewById(R.id.btnBirthday);

        wedding.setOnClickListener(v -> openDesign("Wedding"));
        birthday.setOnClickListener(v -> openDesign("Birthday"));
    }

    void openDesign(String category) {
        Intent i = new Intent(this, DesignActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}