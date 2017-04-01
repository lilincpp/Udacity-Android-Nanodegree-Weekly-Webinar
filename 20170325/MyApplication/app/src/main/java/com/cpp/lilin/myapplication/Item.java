package com.cpp.lilin.myapplication;

/**
 * Created by Colin on 2017/3/31.
 */

public class Item {

    static final int TYPE_TITLE = 1;
    static final int TYPE_CONTENT = 2;


    int type;
    String content;
    String color;
    int spanSize = 1;

    public Item(int type, String color, int spanSize) {
        this.type = type;
        this.color = color;
        this.spanSize = spanSize;
        if (type == Item.TYPE_TITLE) {
            content = "标题 - " + color;
        } else {
            content = "子项 - " + color;
        }
    }
}
