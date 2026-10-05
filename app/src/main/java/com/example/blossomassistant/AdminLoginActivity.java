package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminLoginActivity extends AppCompatActivity {

    private static final String ADMIN_USERNAME = "nithi_admin";
    private static final String ADMIN_PASSWORD = "Nithi@2026";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Admin Login");
        }

        EditText etUsername = findViewById(R.id.etAdminUsername);
        EditText etPassword = findViewById(R.id.etAdminPassword);
        Button btnLogin = findViewById(R.id.btnAdminLogin);

        btnLogin.setOnClickListener(v -> {
            String u = etUsername.getText() == null ? "" : etUsername.getText().toString().trim();
            String p = etPassword.getText() == null ? "" : etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ADMIN_USERNAME.equals(u) && ADMIN_PASSWORD.equals(p)) {
                startActivity(new Intent(AdminLoginActivity.this, AdminDashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

