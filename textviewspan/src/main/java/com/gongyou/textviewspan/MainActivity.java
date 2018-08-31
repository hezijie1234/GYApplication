package com.gongyou.textviewspan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv_span_test);
        SpannableStringBuilder spannable = new SpannableStringBuilder("Text is spantastic!");
        spannable.setSpan(Color.RED,8,12,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

    }
}
