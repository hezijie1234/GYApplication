package com.gongyou.localbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<Student> list = (List<Student>) intent.getSerializableExtra("result");
            Log.e("111", "onReceive: " + list );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastUtils.getInstance(this).register("test",receiver);
    }

    public void onClick(View view) {
        Student student = new Student();
        student.setAge("18");
        student.setName("何子杰");
        List<Student> list = new ArrayList();
        list.add(student);
        LocalBroadcastUtils.getInstance(this).sendBroadcast("test", (Serializable) list);

    }
}
