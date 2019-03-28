package com.gongyou.rvadapterhelp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.gongyou.recycleviewtest.R;
import com.gongyou.rvadapterhelp.bean.MultiItemBean;

import java.util.List;

/**
 * Created by hezijie on 2018/12/7.
 */

public class MultiItemAdapter extends BaseQuickAdapter<MultiItemBean,BaseViewHolder> {


    public MultiItemAdapter(@Nullable List<MultiItemBean> data) {
        super(data);
        //设置多布局的代理
        setMultiTypeDelegate(new MultiTypeDelegate<MultiItemBean>() {
            @Override
            protected int getItemType(MultiItemBean multiItemBean) {
                return multiItemBean.getItemType();
            }
        });
        getMultiTypeDelegate().registerItemType(1, R.layout.item_text_view)
                .registerItemType(2,R.layout.item_image_view)
                .registerItemType(3,R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {
        switch (helper.getItemViewType()){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }
}
