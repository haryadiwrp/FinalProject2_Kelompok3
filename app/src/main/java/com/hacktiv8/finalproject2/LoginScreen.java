package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity {

    private Button btnAdmin, btnStaff, btnAbout, btnMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_screen);

        btnAdmin = findViewById(R.id.loginAdmin);
        btnStaff = findViewById(R.id.loginStaff);
        btnAbout = findViewById(R.id.about);
        btnMember = findViewById(R.id.loginUser);

        btnAdmin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginAdmin.class));
        });

        btnStaff.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginStaff.class));
        });

        btnMember.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginUser.class));
        });

    }
}