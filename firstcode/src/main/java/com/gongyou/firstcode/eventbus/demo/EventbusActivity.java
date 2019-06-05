package com.gongyou.firstcode.eventbus.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.gongyou.firstcode.BaseActivity;
import com.gongyou.firstcode.EventbusActivity2;
import com.gongyou.firstcode.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.gongyou.firstcode.eventbus.demo.MessageEvent.EventCode.A;

/**
 * 参考：https://juejin.im/post/5a1bfe63f265da432e5bbc24
 */
public class EventbusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
    }


    public void sendMessage(View view) {
        //发送的粘性事件可以被stick = false的接受方法收到。
        EventBus.getDefault().postSticky(new MessageEvent<>(A));
    }

    public void receiverStickMessage(View view) {
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMessage(MessageEvent messageEvent) {
        Log.e("111", "EventbusActivity: " + messageEvent.getCode());
        EventBus.getDefault().removeStickyEvent(messageEvent);
    }

    public void startToNext(View view) {
        startActivity(new Intent(this, EventbusActivity2.class));
    }
}
