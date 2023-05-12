package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryClothing extends AppCompatActivity implements View.OnClickListener{

    ImageView tshirt, formal, bottmWear, shoes, iconBack;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_clothing);

        tshirt = findViewById(R.id.img_tshirts);
        formal = findViewById(R.id.img_formals);
        bottmWear = findViewById(R.id.img_celana);
        shoes = findViewById(R.id.img_shoes);
        iconBack = findViewById(R.id.icon_back);

        tshirt.setOnClickListener(this);
        formal.setOnClickListener(this);
        bottmWear.setOnClickListener(this);
        shoes.setOnClickListener(this);
        iconBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_tshirts) {
            Intent intentBaju = new Intent(CategoryClothing.this, RecyclerViewProduct.class);
            intentBaju.putExtra("category", "T-shirt");
            startActivity(intentBaju);
        } else if (view.getId() == R.id.img_formals) {
            Intent intentBaju = new Intent(CategoryClothing.this, RecyclerViewProduct.class);
            intentBaju.putExtra("category", "Formal");
            startActivity(intentBaju);
        } else if (view.getId() == R.id.img_celana) {
            Intent intentCelana = new Intent(CategoryClothing.this, RecyclerViewProduct.class);
            intentCelana.putExtra("category", "Bottom Wear");
            startActivity(intentCelana);
        } else if (view.getId() == R.id.img_shoes) {
            Intent intentShoes = new Intent(CategoryClothing.this, RecyclerViewProduct.class);
            intentShoes.putExtra("category", "Shoes");
            startActivity(intentShoes);
        } else if (view.getId() == R.id.icon_back) {
            Intent intentBack = new Intent(CategoryClothing.this, HomePageUser.class);
            startActivity(intentBack);
        }
    }
}