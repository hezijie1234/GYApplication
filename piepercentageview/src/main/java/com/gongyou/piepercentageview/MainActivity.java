package com.gongyou.piepercentageview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int color = Color.argb(10,255,0,0);
    private int color2 = 0xaaff0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PieView pieView = findViewById(R.id.pieview);
//        List<Pie> list = new ArrayList<>();
//        list.add(new Pie("项目一",5));
//        list.add(new Pie("项目二",3));
//        list.add(new Pie("项目三",4));
//        list.add(new Pie("项目四",7));
//        list.add(new Pie("项目五",1));
//        list.add(new Pie("项目六",9));
//        pieView.setmDataList(list);
//        TextView tvTras = findViewById(R.id.tv_color);
//        tvTras.setBackgroundColor(color2);
    }
}
