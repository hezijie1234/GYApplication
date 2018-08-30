package com.gongyou.piepercentageview;

/**
 * Created by hezijie on 2018/8/30.
 */

public class Pie {

    private String name;  //名字
    private float value; //数值
    private float percentage; //百分比
    private int color = 0;
    private float angle;

    public Pie(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getPercentage() {
        return percentage;
    }
}
