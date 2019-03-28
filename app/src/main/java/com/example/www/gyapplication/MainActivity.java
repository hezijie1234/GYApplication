package com.example.www.gyapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.text);
        final RatingBar ratingBar_Small = (RatingBar)findViewById(R.id.ratingbar_Small);
        final RatingBar ratingBar_Indicator = (RatingBar)findViewById(R.id.ratingbar_Indicator);
        final RatingBar ratingBar_default = (RatingBar)findViewById(R.id.ratingbar_default);
        ratingBar_default.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar_Small.setRating(rating + 0.2f);
                ratingBar_Indicator.setRating(rating);
                Toast.makeText(MainActivity.this, "rating:"+String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
