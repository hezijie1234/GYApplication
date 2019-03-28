package com.gongyou.rvadapterhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongyou.recycleviewtest.R;
import com.gongyou.rvadapterhelp.adapter.AnimationAdapter;
import com.gongyou.rvadapterhelp.bean.AnimationBean;

import java.util.ArrayList;
import java.util.List;

public class AnimationUseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AnimationAdapter animationAdapter;
    private List<AnimationBean> mDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_use);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
    }

    private void initAdapter() {
        for (int i = 0; i < 100; i++) {
            mDataList.add(new AnimationBean());
        }
        animationAdapter = new AnimationAdapter(mDataList);
        animationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        animationAdapter.isFirstOnly(false);
        animationAdapter.setNotDoAnimationCount(4);

        mRecyclerView.setAdapter(animationAdapter);
        animationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AnimationBean item = (AnimationBean) adapter.getItem(position);
                switch (view.getId()){
                    case R.id.tv_name:
                        Toast.makeText(AnimationUseActivity.this,"数据" + position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
