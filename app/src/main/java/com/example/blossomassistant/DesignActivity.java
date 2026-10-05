package com.example.blossomassistant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class DesignActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Choose Design");
        }

        recycler = findViewById(R.id.recycler);
        txtCategory = findViewById(R.id.txtCategory);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        String category = getIntent().getStringExtra("category");
        if (category == null) {
            category = "Wedding";
        }
        txtCategory.setText("Category: " + category);
        ArrayList<DesignModel> list = new ArrayList<>();

        if ("Birthday".equalsIgnoreCase(category)) {
            list.add(new DesignModel("Birthday Theme", R.drawable.birthday_theme, 1200));
            list.add(new DesignModel("Marigold Birthday", R.drawable.marigold_birthday, 1000));
            list.add(new DesignModel("Default Decor", R.drawable.default_decor, 900));
            list.add(new DesignModel("Housewarming Decor", R.drawable.housewarming_decor, 1100));
        } else {
            list.add(new DesignModel("Big Rose Wedding", R.drawable.big_rose_wedding, 1800));
            list.add(new DesignModel("Orchid Wedding", R.drawable.orchid_wedding, 2200));
            list.add(new DesignModel("Small Rose Wedding", R.drawable.small_rose_wedding, 1600));
            list.add(new DesignModel("Engagement Decor", R.drawable.engagement_decor, 2000));
        }

        recycler.setAdapter(new DesignAdapter(this, list, category));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}