package com.cpp.lilin.myapplication6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置我们的数据源
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; ++ i) {
            data.add(randomColor());
        }
        ImageView imageView = (ImageView) findViewById(R.id.iv_head);
        MyRecyclerview recyclerView = (MyRecyclerview) findViewById(R.id.recyclerview);
        MyAdaper myAdaper = new MyAdaper(data);
        //设置我们的自定义布局
        recyclerView.setLayoutManager(new MyLinearLayoutManager(this));
        //设置适配器
        recyclerView.setAdapter(myAdaper);
        //将ImageView传递出去
        recyclerView.setHead(imageView);

    }

    public static String randomColor() {
        String a, r, g, b;
        Random random = new Random();
        a = Integer.toHexString(random.nextInt(256));
        r = Integer.toHexString(random.nextInt(256));
        g = Integer.toHexString(random.nextInt(256));
        b = Integer.toHexString(random.nextInt(256));

        //        a = a.length() == 1 ? "0" + a : a;
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }


}
