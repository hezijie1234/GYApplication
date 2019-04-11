package com.gongyou.firstcode.eventbus.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.gongyou.firstcode.BaseActivity;
import com.gongyou.firstcode.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.gongyou.firstcode.eventbus.demo.MessageEvent.EventCode.A;

public class EventbusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
    }


    public void sendMessage(View view) {
        EventBus.getDefault().post(new MessageEvent<>(A));
    }

    public void receiverStickMessage(View view) {
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMessage(MessageEvent messageEvent){
//        EventBus.getDefault().removeStickyEvent(messageEvent);
        Log.e("111", "onEventMessage: " + messageEvent.getCode() );
    }
}
