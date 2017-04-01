package com.cpp.lilin.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by Colin on 2017/3/23.
 */

public class MyScrollView extends ScrollView {

    private static final String TAG = "MyScrollView";

    int mHeadOriginheight;


    private void init(Context context) {
        mHeadOriginheight = context.getResources().getDimensionPixelSize(R.dimen.head_height);
    }

    public MyScrollView(Context context) {
        super(context);
        init(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private ImageView head;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        head = (ImageView) linearLayout.getChildAt(0);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        smoothScrollTo(0, 0);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int
            scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e(TAG, "overScrollBy: deltaY = " + deltaY + ",scrollY = " + scrollY + ", scrollRangeY = " + scrollRangeY
                + ",maxOverScrollY = " + maxOverScrollY);
        if (scrollY == 0 && deltaY < 0) {
            //过度下拉
            head.getLayoutParams().height = head.getHeight() - deltaY;
            //通知重新绘制吧
            head.requestLayout();
        } else if (scrollY == scrollRangeY && deltaY > 0) {
            //过度上拉
            if (head.getHeight() > mHeadOriginheight) {
                head.getLayoutParams().height = head.getHeight() - deltaY;
                head.requestLayout();
            }
        } else {
            //正常的情况
            if (deltaY > 0) {
                if (head.getHeight() > mHeadOriginheight) {
                    //当头部的高度大于一开始的高度时
                    //先不让ScrollView进行滑动，我们减少头部的高度直到原始的高度为止
                    head.getLayoutParams().height = head.getHeight() - deltaY;
                    head.requestLayout();
                } else {
                    //当头部的高度等于开始的高度时，对ScrollView进行滑动
                    return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                            maxOverScrollX, maxOverScrollY, isTouchEvent);
                }
            } else {
                return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                        maxOverScrollX, maxOverScrollY, isTouchEvent);
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(head.getHeight(), mHeadOriginheight);
                valueAnimator.setDuration(400);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        head.getLayoutParams().height = (int) value;
                        head.requestLayout();
                    }
                });
                valueAnimator.start();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
