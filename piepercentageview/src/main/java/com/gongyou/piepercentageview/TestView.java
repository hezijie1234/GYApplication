package com.gongyou.piepercentageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hezijie on 2018/8/30.
 */

public class TestView extends View {

    private Paint mPaint = new Paint();

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     *
     * 目标是将父控件的测量规格和child view的布局参数LayoutParams相结合，得到一个
     * 最可能符合条件的child view的测量规格。

     * @param spec 父控件的测量规格
     * @param padding 父控件里已经占用的大小
     * @param childDimension child view布局LayoutParams里的尺寸
     * @return child view 的测量规格
     */
//    public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
//        int specMode = MeasureSpec.getMode(spec); //父控件的测量模式
//        int specSize = MeasureSpec.getSize(spec); //父控件的测量大小
//
//        int size = Math.max(0, specSize - padding);
//
//        int resultSize = 0;
//        int resultMode = 0;
//
//        switch (specMode) {
//            // 当父控件的测量模式 是 精确模式，也就是有精确的尺寸了
//            case MeasureSpec.EXACTLY:
//                //如果child的布局参数有固定值，比如"layout_width" = "100dp"
//                //那么显然child的测量规格也可以确定下来了，测量大小就是100dp，测量模式也是EXACTLY
//                if (childDimension >= 0) {
//                    resultSize = childDimension;
//                    resultMode = MeasureSpec.EXACTLY;
//                }
//
//                //如果child的布局参数是"match_parent"，也就是想要占满父控件
//                //而此时父控件是精确模式，也就是能确定自己的尺寸了，那child也能确定自己大小了
//                else if (childDimension == LayoutParams.MATCH_PARENT) {
//                    resultSize = size;
//                    resultMode = MeasureSpec.EXACTLY;
//                }
//                //如果child的布局参数是"wrap_content"，也就是想要根据自己的逻辑决定自己大小，
//                //比如TextView根据设置的字符串大小来决定自己的大小
//                //那就自己决定呗，不过你的大小肯定不能大于父控件的大小嘛
//                //所以测量模式就是AT_MOST，测量大小就是父控件的size
//                else if (childDimension == LayoutParams.WRAP_CONTENT) {
//                    resultSize = size;
//                    resultMode = MeasureSpec.AT_MOST;
//                }
//                break;
//
//            // 当父控件的测量模式 是 最大模式，也就是说父控件自己还不知道自己的尺寸，但是大小不能超过size
//            case MeasureSpec.AT_MOST:
//                //同样的，既然child能确定自己大小，尽管父控件自己还不知道自己大小，也优先满足孩子的需求
//                if (childDimension >= 0) {
//                    resultSize = childDimension;
//                    resultMode = MeasureSpec.EXACTLY;
//                }
//                //child想要和父控件一样大，但父控件自己也不确定自己大小，所以child也无法确定自己大小
//                //但同样的，child的尺寸上限也是父控件的尺寸上限size
//                else if (childDimension == LayoutParams.MATCH_PARENT) {
//                    resultSize = size;
//                    resultMode = MeasureSpec.AT_MOST;
//                }
//                //child想要根据自己逻辑决定大小，那就自己决定呗
//                else if (childDimension == LayoutParams.WRAP_CONTENT) {
//                    resultSize = size;
//                    resultMode = MeasureSpec.AT_MOST;
//                }
//                break;
//
//            // Parent asked to see how big we want to be
//            case MeasureSpec.UNSPECIFIED:
//                if (childDimension >= 0) {
//                    // Child wants a specific size... let him have it
//                    resultSize = childDimension;
//                    resultMode = MeasureSpec.EXACTLY;
//                } else if (childDimension == LayoutParams.MATCH_PARENT) {
//                    // Child wants to be our size... find out how big it should
//                    // be
//                    resultSize = 0;
//                    resultMode = MeasureSpec.UNSPECIFIED;
//                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
//                    // Child wants to determine its own size.... find out how
//                    // big it should be
//                    resultSize = 0;
//                    resultMode = MeasureSpec.UNSPECIFIED;
//                }
//                break;
//        }
//        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(300,300,500,600,mPaint);
        canvas.drawColor(Color.GRAY);
        RectF rec = new RectF(300,300,600,600);
        canvas.drawArc(rec,0,90,true,mPaint);

    }
}
