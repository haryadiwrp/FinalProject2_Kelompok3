package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryElectronic extends AppCompatActivity implements View.OnClickListener{

    ImageView imgBack, imgComputer, imgSmartphone, iconBack;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_electronic);

        imgBack = findViewById(R.id.icon_back);
        imgComputer = findViewById(R.id.img_computer);
        imgSmartphone = findViewById(R.id.img_smartphone);
        iconBack = findViewById(R.id.icon_back);

        imgBack.setOnClickListener(this);
        imgComputer.setOnClickListener(this);
        imgSmartphone.setOnClickListener(this);
        iconBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_computer) {
            Intent intentComputer = new Intent(CategoryElectronic.this, RecyclerViewProduct.class);
            intentComputer.putExtra("category", "Computer");
            startActivity(intentComputer);
        } else if (v.getId() == R.id.img_smartphone) {
            Intent intentSmartphone = new Intent(CategoryElectronic.this, RecyclerViewProduct.class);
            intentSmartphone.putExtra("category", "Smartphone");
            startActivity(intentSmartphone);
        } else if (v.getId() == R.id.icon_back) {
            Intent intentBack = new Intent(CategoryElectronic.this, HomePageUser.class);
            startActivity(intentBack);
        } else if (v.getId() == R.id.icon_back) {
            Intent intentBack = new Intent(CategoryElectronic.this, HomePageUser.class);
            startActivity(intentBack);
        }

    }
}