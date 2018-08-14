package com.gongyou.ttstest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

public class PopupActivity extends AppCompatActivity {
    PopupWindow popupWindow;
    LinearLayout linearLayout;
    //该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    //记录ProgressBar的完成进度
    int status = 0;
    ProgressBar progressBar;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        //创建一个负责更新的进度的Handler
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //表明消息是由该程序发送的。
                if (msg.what == 0x111) {
                    progressBar.setProgress(status);
                    progressBar.setProgress(status);
                }
            }
        };
        //启动线程来执行任务
        thread = new Thread() {
            public void run() {
                while (status < 100) {
                    // 获取耗时操作的完成百分比
                    status = doWork();
                    // 发送消息到Handler
                    Message m = new Message();
                    m.what = 0x111;
                    // 发送消息
                    mHandler.sendMessage(m);
                }
            }
        };

        initData();
    }

    //模拟一个耗时的操作。
    public int doWork() {
        //为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }

    private void initData() {
        linearLayout = findViewById(R.id.ll_parent);
        progressBar = findViewById(R.id.progress_bar);
        popupWindow = new PopupWindow();
        View sharePopView = LayoutInflater.from(this).inflate(R.layout.popup_check, null);
        popupWindow.setContentView(sharePopView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
//        refreshPop.showAsDropDown(gridView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackDrawable(PopupActivity.this, 1f);
            }
        });
    }

    public void setBackDrawable(Activity context, float f) {
        Window window = context.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = f;
        window.setAttributes(lp);
    }

    public void popClick(View view) {
        setBackDrawable(PopupActivity.this, 0.6f);
        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
    }

    public void progressClick(View view) {
        thread.start();
    }
}
