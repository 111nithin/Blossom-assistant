package com.example.blossomassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private TextView designText;
    private TextView priceText;
    private TextView dateValue;
    private TextView timeValue;
    private EditText etName;
    private EditText etPhone;
    private EditText etAddress;
    private Button btnPickDate;
    private Button btnPickTime;
    private Button btnConfirmBooking;

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Booking Details");
        }

        designText = findViewById(R.id.designText);
        priceText = findViewById(R.id.priceText);
        dateValue = findViewById(R.id.dateValue);
        timeValue = findViewById(R.id.timeValue);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        String designName = getIntent().getStringExtra("designName");
        String customization = getIntent().getStringExtra("customization");
        String price = getIntent().getStringExtra("price");
        boolean fromCart = getIntent().getBooleanExtra("fromCart", false);
        ArrayList<String> cartItems = getIntent().getStringArrayListExtra("cartItems");

        if (fromCart) {
            designText.setText("Design: Multiple Items Booking");
        } else {
            if (designName == null || designName.trim().isEmpty()) {
                designName = "Custom Design";
            }
            if (customization == null || customization.trim().isEmpty()) {
                customization = "Standard";
            }
            designText.setText("Design: " + designName + " (" + customization + ")");
        }
        if (price == null || price.trim().isEmpty()) {
            price = "₹0";
        }

        final String finalDesign = fromCart ? "Multiple Items Booking" : designName + " (" + customization + ")";
        final String finalPrice = price;
        priceText.setText("Total: " + finalPrice);

        btnPickDate.setOnClickListener(v -> showDatePicker());
        btnPickTime.setOnClickListener(v -> showTimePicker());

        btnConfirmBooking.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)
                    || TextUtils.isEmpty(address)
                    || TextUtils.isEmpty(selectedDate)
                    || TextUtils.isEmpty(selectedTime)) {
                Toast.makeText(this, "Please fill all booking fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(OrderActivity.this, ConfirmationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);
            intent.putExtra("date", selectedDate);
            intent.putExtra("time", selectedTime);
            intent.putExtra("designName", finalDesign);
            intent.putExtra("price", finalPrice);
            intent.putExtra("fromCart", fromCart);
            if (cartItems != null) {
                intent.putStringArrayListExtra("cartItems", cartItems);
            }
            startActivity(intent);
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate = String.format(Locale.getDefault(),
                            "%02d-%02d-%04d", dayOfMonth, month + 1, year);
                    dateValue.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    selectedTime = String.format(Locale.getDefault(),
                            "%02d:%02d", hourOfDay, minute);
                    timeValue.setText(selectedTime);
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}