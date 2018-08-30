package com.gongyou.piepercentageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView pieView = findViewById(R.id.pieview);
        List<Pie> list = new ArrayList<>();
        list.add(new Pie("项目一",5));
        list.add(new Pie("项目二",3));
        list.add(new Pie("项目三",4));
        list.add(new Pie("项目四",7));
        list.add(new Pie("项目五",1));
        list.add(new Pie("项目六",9));
        pieView.setmDataList(list);
    }
}
