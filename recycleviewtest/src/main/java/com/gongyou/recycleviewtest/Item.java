package com.gongyou.recycleviewtest;

import org.json.JSONObject;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2016/11/9.
 * Email ${EMAIL}
 */

public class Item {
    private String imageUrl;
    private String name;

    public static Item createItmfromJson(JSONObject json) {
        Item ret = new Item();
        ret.name = json.optString("myname");
        ret.imageUrl = json.optString("smallpic");
        return ret;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
