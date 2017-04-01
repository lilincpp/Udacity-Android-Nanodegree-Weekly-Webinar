package com.cpp.lilin.myapplication6;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Colin on 2017/3/25.
 */

public class MyLinearLayoutManager extends LinearLayoutManager {

    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    private MyRecyclerview myRecyclerview;

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        myRecyclerview = (MyRecyclerview) view;
    }


    private static final String TAG = "MyLinearLayoutManager";

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //dy>0 手向上滑动
        //dy<0 手向下滑动
        //scrolled是实际滑动的距离，dy是用户滑动的距离
        // 过度的向下滑动(因为没得滑动了)，实际上Recyclerview是不进行滑动的(scrolled==0)
        final int scrolled = super.scrollVerticallyBy(dy, recycler, state);
        Log.e(TAG, "scrollVerticallyBy: " + dy + ",scrolled = " + scrolled);
        //100DP，90dp,10dp
        final int over = dy - scrolled;
        if (over < 0) {
            //过度向下滑动
            myRecyclerview.head.getLayoutParams().height = myRecyclerview.head.getHeight() - over;
            myRecyclerview.head.requestLayout();
        } else if (over > 0) {
            //过度向上
            //
        } else {
            //正常滑动
            if (dy > 0) {
                //向上滑动
                //只有当前高度大于一开始的原始高度的时候，我们才去改变ImageView的高度
                if (myRecyclerview.head.getHeight() > myRecyclerview.originHeight) {
                    myRecyclerview.head.getLayoutParams().height = myRecyclerview.head.getHeight() - dy;
                    myRecyclerview.head.requestLayout();
                }
            }
        }
        return scrolled;
    }
}
