package com.gongyou.ttstest;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private TextToSpeech spp;
    private SpeechUtils instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s = getResources().getString(R.string.app_name);
//        spp = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    int result = spp.setLanguage(Locale.CHINA);
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Log.e("111", "onInit: " + "" );
//                    }
//                }
//            }
//        });
        instance = SpeechUtils.getInstance(this);

    }

    public void onClick(View view) {
        instance.speakText("图福建打卡成功");
    }


}
