package com.gongyou;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gongyou.recycleviewtest.R;


/**
 * 可收缩展开的自定义布局
 */
public class ExpandLayout extends LinearLayout {

    private View    rootView;
    private int     viewHeight;
    private boolean isExpand;
    private long    duration;
    private boolean lock;
    private int     minHeight;

    public ExpandLayout(Context context) {
        this(context, null);
    }

    public ExpandLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ExpandLayout);
        //布局最小高度
        minHeight = a.getInteger(R.styleable.ExpandLayout_minHeight, 0);
        //动画执行时间
        duration = a.getInteger(R.styleable.ExpandLayout_duration, 300);
        //默认是否展开
        isExpand = a.getBoolean(R.styleable.ExpandLayout_isExpand, false);
        a.recycle();
    }

    private void initView() {
        rootView = this;
        setViewDimensions();
    }

    public void initExpand(boolean isExpand) {
        this.isExpand = isExpand;
        setViewDimensions();
    }

    public void setAnimationDuration(long duration) {
        this.duration = duration;
    }

    private void setViewDimensions() {
        rootView.post(new Runnable() {
            @Override
            public void run() {
                if (viewHeight <= 0) {
                    viewHeight = rootView.getMeasuredHeight();
                }
                setViewHeight(rootView, isExpand ? viewHeight : minHeight);
            }
        });
    }

    public static void setViewHeight(View view, int height) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.requestLayout();
    }

    private void animateToggle(long animationDuration) {
        ValueAnimator heightAnimation = isExpand ?
                ValueAnimator.ofFloat(0f, viewHeight) : ValueAnimator.ofFloat(viewHeight, 0f);
        heightAnimation.setDuration(animationDuration / 2);
        heightAnimation.setStartDelay(animationDuration / 2);

        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) (float) animation.getAnimatedValue();
                setViewHeight(rootView, value < minHeight ? minHeight : value);
                if (value == viewHeight || value == minHeight) {
                    lock = false;
                }
            }
        });

        heightAnimation.start();
        lock = true;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void collapse() {
        isExpand = false;
        animateToggle(duration);
    }

    public void expand() {
        isExpand = true;
        animateToggle(duration);
    }

    public void toggleExpand() {
        if (lock) {
            return;
        }
        if (isExpand) {
            collapse();
        } else {
            expand();
        }
    }

    /**
     * 设置布局最小高度。如果在xml中不知道最小高度应该是多少，就在代码中调用此方法。
     * 比如最小高度是TexView的一行高度，就传入TextView.getLineHeight();
     * @param minHeight
     */
    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }
}
