package com.codepath.googleimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.codepath.googleimagesearch.R;
import com.codepath.googleimagesearch.constants.ExtraKeys;
import com.codepath.googleimagesearch.models.google.Image;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Image image = (Image) getIntent().getSerializableExtra(ExtraKeys.IMAGE);
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Picasso.with(this).load(image.getUrl()).into(ivImage);
    }

}
