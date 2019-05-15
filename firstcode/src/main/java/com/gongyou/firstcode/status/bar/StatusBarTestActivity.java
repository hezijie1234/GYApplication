package com.gongyou.firstcode.status.bar;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gongyou.firstcode.R;

public class StatusBarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_test);
        transparencyBar();
        //设置状态栏高度
        setStatusBarHight((LinearLayout) findViewById(R.id.status_bar));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void transparencyBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        );
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setStatusBarHight(LinearLayout linear_bar) {
        if (linear_bar != null) {
            linear_bar.setVisibility(View.INVISIBLE);
            linear_bar.setBackgroundColor(Color.TRANSPARENT);
            //动态的设置隐藏布局的高度  ☆☆☆☆☆☆外层布局必须是 LinearLayout   ☆☆☆☆☆☆☆☆☆
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
            params.height = getStatusBarHeight();
            linear_bar.setLayoutParams(params);
        }
    }
}
