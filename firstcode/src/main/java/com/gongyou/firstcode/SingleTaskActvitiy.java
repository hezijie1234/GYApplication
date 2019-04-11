package com.gongyou.firstcode;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gongyou.firstcode.eventbus.demo.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Android中的定时任务一般有两种实现方式，一种是使用Java API里提供的Timer类，一种是使用Android的Alarm机制。这两种方式在多数情况下都能实现类似的效果，
 * 但Timer有一个明显的短板，它并不太适用于那些需要长期在后台运行的定时任务。我们都知道，为了能让电池更加耐用，每种手机都会有自己的休眠策略，
 * Android手机就会在长时间不操作的情况下自动让CPU进入到睡眠状态，这就有可能导致Timer中的定时任务无法正常运行。而Alarm则具有唤醒CPU的功能，
 * 它可以保证在大多数情况下需要执行定时任务的时候CPU都能正常工作。需要注意，这里唤醒CPU和唤醒屏幕完全不是一个概念，千万不要产生混淆。
 */
public class SingleTaskActvitiy extends BaseActivity {

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

    public void alarmClick(View view) {
        Intent intent = new Intent(this,AlarmService.class);
        startService(intent);
    }

    public void receiverStickMessage(View view) {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMessage(MessageEvent messageEvent){
        Log.e("111", "onEventMessage: " + messageEvent.getCode() );
    }
}
