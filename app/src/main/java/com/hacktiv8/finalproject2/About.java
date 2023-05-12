package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About extends AppCompatActivity implements View.OnClickListener{

    ImageView imgInsta, imgGithub, imgLinkedin, iconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imgInsta = findViewById(R.id.img_instagram);
        imgGithub = findViewById(R.id.img_github);
        imgLinkedin = findViewById(R.id.img_linkedin);
        iconBack = findViewById(R.id.icon_back);

        imgInsta.setOnClickListener(this);
        imgGithub.setOnClickListener(this);
        imgLinkedin.setOnClickListener(this);
        iconBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_instagram) {
            Intent intentInsta = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"));
            startActivity(intentInsta);
        } else if (view.getId() == R.id.img_github) {
            Intent intentGithub = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
            startActivity(intentGithub);
        } else if (view.getId() == R.id.img_linkedin) {
            Intent intentLinkedin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com"));
            startActivity(intentLinkedin);
        } else if (view.getId() == R.id.icon_back) {
            Intent intentBack = new Intent(About.this, LoginScreen.class);
            startActivity(intentBack);
        }
    }
}