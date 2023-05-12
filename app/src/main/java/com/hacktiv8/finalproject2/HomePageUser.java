package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePageUser extends AppCompatActivity implements View.OnClickListener{

    ImageView imgBaju, imgElektronik, imgBuku, imgOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_user);

        imgBaju = findViewById(R.id.img_baju);
        imgElektronik = findViewById(R.id.img_elektronik);
        imgBuku = findViewById(R.id.img_buku);
        imgOthers = findViewById(R.id.img_others);

        imgBaju.setOnClickListener(this);
        imgElektronik.setOnClickListener(this);
        imgBuku.setOnClickListener(this);
        imgOthers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_baju) {
            Intent intentBaju = new Intent(HomePageUser.this, CategoryClothing.class);
            startActivity(intentBaju);
        } else if (v.getId() == R.id.img_elektronik) {
            Intent intentElektronik = new Intent(HomePageUser.this, CategoryElectronic.class);
            startActivity(intentElektronik);
        } else if (v.getId() == R.id.img_buku) {
            Intent intentBuku = new Intent(HomePageUser.this, RecyclerViewProduct.class);
            intentBuku.putExtra("category", "Books");
            startActivity(intentBuku);
        } else if (v.getId() == R.id.img_others) {
            Intent intentOthers = new Intent(HomePageUser.this, RecyclerViewProduct.class);
            intentOthers.putExtra("category", "Others");
            startActivity(intentOthers);
        }

    }
}