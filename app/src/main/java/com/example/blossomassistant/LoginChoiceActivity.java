package com.example.blossomassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Blossom Assistant Login");
        }

        Button btnUserLogin = findViewById(R.id.btnUserLogin);
        Button btnAdminLogin = findViewById(R.id.btnAdminLoginEntry);

        btnUserLogin.setOnClickListener(v ->
                startActivity(new Intent(LoginChoiceActivity.this, MainActivity.class)));

        btnAdminLogin.setOnClickListener(v ->
                startActivity(new Intent(LoginChoiceActivity.this, AdminLoginActivity.class)));
    }
}

