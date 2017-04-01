package com.cpp.lilin.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Colin on 2017/3/23.
 */

public class MyRecyclerview extends RecyclerView {


    public MyRecyclerview(Context context) {
        super(context);

    }

    public MyRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MyRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        //AT_MOST表示父容器将给其分配最大的空间
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }




    private static final String TAG = "MyRecyclerview";


}
