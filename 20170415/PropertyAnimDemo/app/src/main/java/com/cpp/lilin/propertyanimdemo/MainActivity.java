package com.cpp.lilin.propertyanimdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout mRelativeLayout;
    private ImageView mIvFavorite;
    private MyDrawingView myDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDrawingView = (MyDrawingView) findViewById(R.id.draw_view);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        mIvFavorite = (ImageView) findViewById(R.id.movie_favorite);
        mIvFavorite.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myDrawingView.initStartPosition(
                        mIvFavorite.getX()+mIvFavorite.getWidth()/2,
                        mIvFavorite.getY()+mIvFavorite.getHeight()/2
                );

                myDrawingView.initStartTextPosition(
                        mRelativeLayout.getHeight() / 2
                );
            }
        });
        mIvFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_favorite:
                favorite = ! favorite;
                if (favorite) {
                    myDrawingView.startAnim();
                } else {
                    myDrawingView.endAnim();
                }
                break;
        }
    }

    private boolean favorite = false;

    private static final String TAG = "MainActivity";
}
