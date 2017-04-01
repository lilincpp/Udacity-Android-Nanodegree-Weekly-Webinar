package com.cpp.lilin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Item> data = new ArrayList<>();

    //将一行分成几份
    static final int MAX_SPAN_SIZE = 4;

    {
        data.addAll(getItemList(4, MAX_SPAN_SIZE / 2));
        Item title1 = new Item(Item.TYPE_TITLE, randomColor(), MAX_SPAN_SIZE);
        data.add(title1);
        data.addAll(getItemList(16, 1));

        for (int i = 0; i < 40; ++ i) {
            if (i % 4 == 0) {
                Item title = new Item(Item.TYPE_TITLE, randomColor(), MAX_SPAN_SIZE);
                data.add(title);
            } else {
                Item item = new Item(Item.TYPE_CONTENT, randomColor(), MAX_SPAN_SIZE / 2);
                data.add(item);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRecyclerview myRecyclerview = (MyRecyclerview) findViewById(R.id.recyclerview);


        MyAdaper myAdaper = new MyAdaper(data);

        myRecyclerview.setAdapter(myAdaper);
        myRecyclerview.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, MAX_SPAN_SIZE);
        myRecyclerview.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return data.get(position).spanSize;
            }
        });
    }

    static String randomColor() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }

    static List<Item> getItemList(final int count, final int spanSize) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < count; ++ i) {
            Item item = new Item(Item.TYPE_CONTENT, randomColor(), spanSize);
            items.add(item);
        }
        return items;
    }
}
