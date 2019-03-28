package com.gongyou.rvadapterhelp.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gongyou.recycleviewtest.R;
import com.gongyou.rvadapterhelp.bean.AnimationBean;

import java.util.List;

/**
 * Created by hezijie on 2018/12/6.
 */

public class AnimationAdapter extends BaseMultiItemQuickAdapter<AnimationBean,BaseViewHolder> {

    public AnimationAdapter( @Nullable List<AnimationBean> data) {
//        super(R.layout.item_animation, data);
        super(data);
        addItemType(1,R.layout.item_animation);

    }

    @Override
    protected void convert(BaseViewHolder helper, AnimationBean item) {
        TextView tvName = (TextView) helper.getView(R.id.tv_name);
        tvName.setText("数据：" + helper.getLayoutPosition());
        helper.addOnClickListener(R.id.tv_name);
    }
}
