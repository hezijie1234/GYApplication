package com.gongyou.ttstest;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        spp = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

    }

    public void onClick(View view)  {
        throw new MyException("报了一个自定义异常");
    }
}
