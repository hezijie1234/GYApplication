package com.gongyou.piepercentageview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by hezijie on 2018/8/30.
 * 一个百分比的自定义圆形。
 */

public class PieView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private List<Pie> mDataList;
    private Paint mPaint = new Paint();
    private float mStartAngle;
    private int mWidth,mHeight;

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(0xff0000);
        Log.d("111", "onDraw: " + getTop() + "-" + getLeft() + "-" + getRight() + "-" + getBottom());
        if (null == mDataList){
            return;
        }
        float currentAngle = mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);
        float r = (float) (Math.min(mWidth,mHeight) / 2 * 0.8);
        for (int i = 0; i < mDataList.size(); i++) {
            Pie pie = mDataList.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(-r,-r,r,r,currentAngle,pie.getAngle(),true,mPaint);
            currentAngle += pie.getAngle();
        }
    }

    public void setmDataList(List<Pie> mDataList) {
        this.mDataList = mDataList;
        initData(mDataList);
        invalidate();
    }

    private void initData(List<Pie> list){
        if (null == list || list.size() == 0){
            return;
        }

        float sumValue = 0;
        for (int i = 0; i < list.size(); i++) {
            sumValue += list.get(i).getValue();
            int j = i % mColors.length;
            list.get(i).setColor(mColors[j]);
        }

        for (int i = 0; i < list.size(); i++) {
            Pie pie = list.get(i);
            float percentege = pie.getValue() / sumValue;  //百分比
            float angle = percentege * 360;
            pie.setPercentage(percentege);
            pie.setAngle(angle);

        }
    }

    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }
}
