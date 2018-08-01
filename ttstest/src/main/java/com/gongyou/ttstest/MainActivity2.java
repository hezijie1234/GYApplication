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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spp = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = spp.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("111", "onInit: " + "" );
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        spp.setPitch(1.5f); // 在系统设置里也可以修改音调
        spp.speak("card ok",TextToSpeech.QUEUE_FLUSH,null);
    }


}
