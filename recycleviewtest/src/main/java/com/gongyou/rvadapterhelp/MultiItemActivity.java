package com.gongyou.rvadapterhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gongyou.recycleviewtest.R;
import com.gongyou.rvadapterhelp.adapter.MultiItemAdapter;
import com.gongyou.rvadapterhelp.bean.MultiItemBean;

import java.util.ArrayList;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MultiItemBean> mDataList = new ArrayList<>();
    private MultiItemAdapter multiItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        multiItemAdapter = new MultiItemAdapter(mDataList);
        multiItemAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        multiItemAdapter.isFirstOnly(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        for (int i = 0; i < 5; i++) {
            mDataList.add(new MultiItemBean(2,1));
            mDataList.add(new MultiItemBean(1,3));
            mDataList.add(new MultiItemBean(3,4));
            mDataList.add(new MultiItemBean(3,2));
            mDataList.add(new MultiItemBean(3,2));
        }
        multiItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDataList.get(position).getSpanSize();
            }
        });
        mRecyclerView.setAdapter(multiItemAdapter);
    }
}
