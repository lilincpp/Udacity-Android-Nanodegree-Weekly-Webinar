package com.cpp.lilin.myapplication6;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Colin on 2017/3/25.
 */

public class MyRecyclerview extends RecyclerView {

    ImageView head;
    int originHeight;

    //拿到ImageView的引用
    public void setHead(ImageView head) {
        this.head = head;
    }

    public MyRecyclerview(Context context) {
        super(context);
        init(context);
    }

    public MyRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context) {
        originHeight = context.getResources().getDimensionPixelSize(R.dimen.head_height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                //当手指离开屏幕的时候，就会调用该方法中的ACTION_UP事件
                //ofFloat(1,2,3,4,5); value从1变化到2再变化到3...
                //500dp~240dp 490.111~ ...~240dp
                //属性动画
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(head.getHeight(), originHeight);
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
        return super.onTouchEvent(e);
    }
}
