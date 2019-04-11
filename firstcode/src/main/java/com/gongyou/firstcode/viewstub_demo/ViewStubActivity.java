package com.gongyou.firstcode.viewstub_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.gongyou.firstcode.R;

/**ViewStub使用的是惰性加载的方式，即使将其放置于布局文件中，如果没有进行加载那就为空，不像其它控件一样只要布局文件中声明就会存在。
 那ViewStub适用于场景呢？通常用于网络请求页面失败的显示。一般情况下若要实现一个网络请求失败的页面，我们是不是使用两个View呢，一个隐藏，一个显示。试想一下，如果网络状况良好，并不需要加载失败页面，但是此页面确确实实已经加载完了，无非只是隐藏看不见而已。如果使用ViewStub，在需要的时候才进行加载，不就达到节约内存提高性能的目的了吗？
 ---------------------
 * viewStub的布局中不能使用merge
 */
public class ViewStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
    }

    public void inflate(View view) {
        ViewStub viewById = findViewById(R.id.vs_demo);
        viewById.inflate();
    }

    public void setData(View view) {
        TextView tvName = findViewById(R.id.tv_name);
    }
}
