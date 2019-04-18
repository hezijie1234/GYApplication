package com.gongyou.firstcode.cachedir.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gongyou.firstcode.R;

/**
 * 测试不用文件存储的区别可以参考：https://www.jianshu.com/p/ebca517ae7d5
 * 清除数据和清除缓存有什么却别呢？请参考：https://blog.csdn.net/u011016373/article/details/86304321
 */
public class CacheDirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_dir);
    }
}
