package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        // Mendapatkan data yang dikirim melalui Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name_product");
        String description = intent.getStringExtra("description");
        String price = intent.getStringExtra("price");
        String brand = intent.getStringExtra("brand");
        String quantity = intent.getStringExtra("quantity");
        String image = intent.getStringExtra("image_url");

        // Mengambil id dari view
        TextView headline = findViewById(R.id.detailHeadline);
        TextView brandName = findViewById(R.id.detailBrandName);
        TextView descriptionName = findViewById(R.id.detailDescription1);
        TextView priceTextView = findViewById(R.id.detailPrice);
        TextView quantityTextView = findViewById(R.id.detailQuantity);
        ImageView imageView = findViewById(R.id.imageDetailProduct);

        // Menampilkan data yang dikirim melalui Intent
        headline.setText(name);
        brandName.setText(brand);
        descriptionName.setText(description);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);

        // Menampilkan gambar
        Glide.with(this)
                .load(image)
                .into(imageView);
    }

}