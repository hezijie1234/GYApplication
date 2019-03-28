package com.gongyou.piepercentageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hezijie on 2018/8/31.
 */

public class CanvasTestView extends View{
    private Paint mPaint = new Paint();
    public CanvasTestView(Context context) {
        super(context);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        mPaint.setColor(Color.RED);
        //如果不设置画笔的宽度，或者设置为1时，画出的矩形没有下边
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
    int mWidth;
    int mHeight;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);

//        canvas.translate(mWidth / 2,mHeight / 2);
//        RectF rectF = new RectF(0,-400,400,0);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF,mPaint);
//        canvas.scale(-0.5f,-0.5f,0,0);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(rectF,mPaint);
        canvas.translate(mWidth /2,mHeight /2);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,300,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0,0,280,mPaint);
        for (int i = 0; i < 360; i+=10) {
            mPaint.setColor(Color.GREEN);
            canvas.drawLine(280,0,300,0,mPaint);
            canvas.rotate(10);
        }
    }
}
