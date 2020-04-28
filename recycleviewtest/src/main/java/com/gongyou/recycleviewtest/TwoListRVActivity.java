package com.gongyou.recycleviewtest;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gongyou.ExpandLayout;

public class TwoListRVActivity extends AppCompatActivity {
    private ExpandLayout expandLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_list_rv);
        expandLayout = findViewById(R.id.el_expand);
        TextView tvTest  = findViewById(R.id.tv_test);
        tvTest.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvTest.getPaint().setAntiAlias(true);//抗锯齿
    }

    public void expandOr(View view) {
        if (expandLayout.isExpand()){
            expandLayout.collapse();
        }else {
            expandLayout.expand();
        }

    }
}
