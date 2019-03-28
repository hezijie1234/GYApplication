package com.gongyou.rvadapterhelp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by hezijie on 2018/12/7.
 */

public class MultiItemBean {

    public MultiItemBean(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getItemType() {
        return itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    private int itemType;
    private int spanSize;

}
