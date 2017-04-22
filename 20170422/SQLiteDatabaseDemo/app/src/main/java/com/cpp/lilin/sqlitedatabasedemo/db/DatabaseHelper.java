package com.cpp.lilin.sqlitedatabasedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Colin on 2017/4/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "school.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        //指定数据库的名称和数据库的版本
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_CLASSES = "classes";
    public static final String TABLE_STUDENTS = "students";

    private static final String CREATE_TABLE_CLASSES = "CREATE TABLE " + TABLE_CLASSES + " ("
            + "id integer primary key autoincrement,"
            + "major text not null"
            + ")";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENTS + " ("
            + "id integer primary key autoincrement,"
            + "name text not null check(length(name)>=2),"
            + "tel text not null unique,"
            + "cls_id integer not null,"
            + "foreign key(cls_id) references " + TABLE_CLASSES + "(id)"
            + ")";

    private static final String OPEN_FOREIGN_KEY = "pragma foreign_keys=on;";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: ");
        //执行数据库语句的地方
        db.execSQL(CREATE_TABLE_CLASSES);
        //        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库升级的地方
        Log.e(TAG, "onUpgrade: ");

        //直接删除表，然后建立新表，非常的暴力
        //删除表中的所有数据
        //不建议
        //        db.execSQL("drop table if exists " + TABLE_STUDENTS);
        //        db.execSQL("drop table if exists " + TABLE_CLASSES);
        //        onCreate(db);

        //建议的方法
        //检索磁盘上的数据库版本，进行有条件的更新
        switch (oldVersion) {
            case 1:
            case 2:
                db.execSQL(CREATE_TABLE_STUDENTS);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.e(TAG, "onOpen: ");
        db.execSQL(OPEN_FOREIGN_KEY);
    }

    private static final String TAG = "DatabaseHelper";
}
