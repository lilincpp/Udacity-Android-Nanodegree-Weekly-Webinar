package com.cpp.lilin.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //显式Intent启动
                //完全限定要启动的Activity的类名
                //                Intent intent = new Intent();
                //第一种
                //                intent.setComponent(new ComponentName(this, SecondActivity.class));
                //                intent.setClass(this,SecondActivity.class);

                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("Message", "hello");

                startActivity(intent);

                break;
            case R.id.btn2:
                //隐式Intent启动
                Intent intent1=new Intent();
//                intent1.setComponent(new ComponentName(this,MainActivity.class));
                intent1.setAction(Intent.ACTION_SEND);
                intent1.addCategory("com.cpp.lilin.intentdemo.MY_CATEGORY1");
                startActivity(intent1);
                break;
        }
    }
}
