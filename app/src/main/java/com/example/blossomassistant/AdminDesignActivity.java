package com.example.blossomassistant;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminDesignActivity extends AppCompatActivity implements AdminDesignAdapter.DesignActionListener {

    private EditText etName;
    private EditText etPrice;
    private Spinner spCategory;
    private Spinner spImage;
    private Button btnAdd;

    private AdminDesignAdapter adapter;

    private final String[] categories = {"Wedding", "Birthday"};
    private final String[] imageLabels = {
            "Big Rose Wedding",
            "Orchid Wedding",
            "Small Rose Wedding",
            "Engagement Decor",
            "Birthday Theme",
            "Marigold Birthday",
            "Default Decor",
            "Housewarming Decor"
    };
    private final int[] imageResIds = {
            R.drawable.big_rose_wedding,
            R.drawable.orchid_wedding,
            R.drawable.small_rose_wedding,
            R.drawable.engagement_decor,
            R.drawable.birthday_theme,
            R.drawable.marigold_birthday,
            R.drawable.default_decor,
            R.drawable.housewarming_decor
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_design);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Manage Designs");
        }

        DesignStore.ensureSeedData();

        etName = findViewById(R.id.etDesignName);
        etPrice = findViewById(R.id.etDesignPrice);
        spCategory = findViewById(R.id.spDesignCategory);
        spImage = findViewById(R.id.spDesignImage);
        btnAdd = findViewById(R.id.btnAddDesign);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(catAdapter);

        ArrayAdapter<String> imageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imageLabels);
        imageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spImage.setAdapter(imageAdapter);

        RecyclerView recycler = findViewById(R.id.recyclerAdminDesigns);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdminDesignAdapter(DesignStore.designs, this);
        recycler.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addDesign());
    }

    private void addDesign() {
        String name = etName.getText() == null ? "" : etName.getText().toString().trim();
        String priceStr = etPrice.getText() == null ? "" : etPrice.getText().toString().trim();
        String category = spCategory.getSelectedItem() == null ? "Wedding" : spCategory.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
            Toast.makeText(this, "Please enter design name and price", Toast.LENGTH_SHORT).show();
            return;
        }

        int price;
        try {
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Price must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        int imgIndex = Math.max(0, spImage.getSelectedItemPosition());
        if (imgIndex >= imageResIds.length) imgIndex = 0;

        DesignStore.designs.add(new DesignModel(name, imageResIds[imgIndex], price, category));
        adapter.notifyItemInserted(DesignStore.designs.size() - 1);

        etName.setText("");
        etPrice.setText("");
        spCategory.setSelection(0);
        spImage.setSelection(0);
        Toast.makeText(this, "Design added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete(int position) {
        if (position < 0 || position >= DesignStore.designs.size()) return;
        DesignStore.designs.remove(position);
        adapter.notifyItemRemoved(position);
        Toast.makeText(this, "Design deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartEdit(int position) {
        if (position < 0 || position >= DesignStore.designs.size()) return;
        DesignModel model = DesignStore.designs.get(position);

        etName.setText(model.getName());
        etPrice.setText(String.valueOf(model.getPrice()));
        spCategory.setSelection("Birthday".equalsIgnoreCase(model.getCategory()) ? 1 : 0);

        int foundIndex = 0;
        for (int i = 0; i < imageResIds.length; i++) {
            if (imageResIds[i] == model.getImage()) {
                foundIndex = i;
                break;
            }
        }
        spImage.setSelection(foundIndex);

        btnAdd.setText("Update Design");
        btnAdd.setOnClickListener(v -> applyEdit(position));
    }

    private void applyEdit(int position) {
        String name = etName.getText() == null ? "" : etName.getText().toString().trim();
        String priceStr = etPrice.getText() == null ? "" : etPrice.getText().toString().trim();
        String category = spCategory.getSelectedItem() == null ? "Wedding" : spCategory.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
            Toast.makeText(this, "Please enter design name and price", Toast.LENGTH_SHORT).show();
            return;
        }

        int price;
        try {
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Price must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        int imgIndex = Math.max(0, spImage.getSelectedItemPosition());
        if (imgIndex >= imageResIds.length) imgIndex = 0;

        DesignStore.designs.set(position, new DesignModel(name, imageResIds[imgIndex], price, category));
        adapter.notifyItemChanged(position);

        resetAddMode();
        Toast.makeText(this, "Design updated", Toast.LENGTH_SHORT).show();
    }

    private void resetAddMode() {
        etName.setText("");
        etPrice.setText("");
        spCategory.setSelection(0);
        spImage.setSelection(0);
        btnAdd.setText("Add Design");
        btnAdd.setOnClickListener(v -> addDesign());
    }

    @Override
    public void onCancelEdit() {
        resetAddMode();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

