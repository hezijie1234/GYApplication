package com.gongyou.firstcode;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleTaskActvitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task_actvitiy);
        Person person = getIntent().getParcelableExtra("data");
        Log.e("111", "onCreate: " + person );
    }

    public void intentClick(View view) {
        startActivity(new Intent(this,SingleTaskActvitiy.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Log.e("111", "onCreate: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("111", "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("111", "onResume: " );
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("111", "onNewIntent: 执行了" );
    }
}
