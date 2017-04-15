package com.cpp.lilin.propertyanimdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Colin on 2017/4/15.
 */
//自绘控件继承View
public class MyDrawingView extends View {

    private static final String MESSAGE = "收藏成功";
    private static final int START_RADIUS = 0;
    private int END_RADIUS;

    private float startX, startY;
    private float startTextX, startTextY;
    private float radius;
    private int displayWidth;

    private int textWidth, textHeight;

    private Paint mPaint;
    private Paint mTextPaint;

    {
        startX = 0;
        startY = 0;
        radius = 0;
        //初始化画笔
        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置画笔的颜色
        mPaint.setColor(Color.parseColor("#FF4081"));
        //FILL填充，STROKE线条
        mPaint.setStyle(Paint.Style.FILL);

        //初始化画笔
        mTextPaint = new Paint();
        //设置抗锯齿
        mTextPaint.setAntiAlias(true);
        //设置画笔的颜色
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(100);
        mTextPaint.setAlpha(0);
    }

    public MyDrawingView(Context context) {
        super(context);
        init(context);
    }

    public MyDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //获取屏幕的高度
        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        END_RADIUS = displayWidth;

        //测量文章的宽高
        Rect rect = new Rect();
        mTextPaint.getTextBounds(MESSAGE, 0, MESSAGE.length(), rect);
        textWidth = rect.width();
        textHeight = rect.height();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(startX, startY, radius, mPaint);
        canvas.drawText(MESSAGE, startTextX, startTextY, mTextPaint);
    }

    public void initStartPosition(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void initStartTextPosition(float y) {
        startTextX = displayWidth / 2 - textWidth / 2;
        startTextY = startY + y - textHeight / 2;
    }

    private void startTextAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 255);
        valueAnimator.setDuration(400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.e(TAG, "onAnimationUpdate: " + value);
                mTextPaint.setAlpha((int) value);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(START_RADIUS, END_RADIUS);
        valueAnimator.setDuration(600);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                radius = value;
                //请求重绘
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startTextAnim();
            }
        });
        valueAnimator.start();
    }

    public void endAnim() {
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(END_RADIUS, START_RADIUS);
        valueAnimator.setDuration(600);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                radius = value;
                //请求重绘
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mTextPaint.setAlpha(0);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    interface listener{
        void onCallback();

    }

    private static final String TAG = "MyDrawingView";

}
