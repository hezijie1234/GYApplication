package com.gongyou.firstcode;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gongyou.firstcode.cachedir.demo.CacheDirActivity;
import com.gongyou.firstcode.contraintlayout.demo.ConstraintLayoutDemoActivity;
import com.gongyou.firstcode.eventbus.demo.EventbusActivity;
import com.gongyou.firstcode.eventbus.demo.MessageEvent;
import com.gongyou.firstcode.rxjava.demo.RxjavaStudyActivity;
import com.gongyou.firstcode.status.bar.StatusBarTestActivity;
import com.gongyou.firstcode.viewstub_demo.ViewStubActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
//        EventBus.getDefault().postSticky(new MessageEvent<>(MessageEvent.EventCode.B));
    }

    public void intentClick(View view) {
        Person person = new Person();
        person.setAge(18);
        person.setName("子杰");
        person.setSex(true);
        Author author = new Author("曦俊",20);
        person.setAuthor(author);
        List<String> mNameList = new ArrayList<>();
        mNameList.add("小明");
        mNameList.add("小红");
        person.setTestList(mNameList);
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("阿贵",25));
        authorList.add(new Author("曾凡人",30));
        person.setmData(authorList);
        startActivity(new Intent(this,SingleTaskActvitiy.class).putExtra("data",person));

    }

    /**
     * 进入eventbus测试页面
     * @param view
     */
    public void eventBusTest(View view) {
        startActivity(new Intent(this, EventbusActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false)
    public void onEventMessage(MessageEvent event){
        Log.e("111", "MainActivity: " + event.getCode() );
    }

    public void viewStubTest(View view) {
        startActivity(new Intent(this, ViewStubActivity.class));
    }

    public void fileMemTest(View view) {
        startActivity(new Intent(this, CacheDirActivity.class));
    }

    public void rxjavaText(View view) {
        startActivity(new Intent(this, RxjavaStudyActivity.class));
    }

    public void statusBarTest(View view) {
        startActivity(new Intent(this, StatusBarTestActivity.class));
    }

    public void constraintTest(View view) {
        startActivity(new Intent(this, ConstraintLayoutDemoActivity.class));
    }
}
