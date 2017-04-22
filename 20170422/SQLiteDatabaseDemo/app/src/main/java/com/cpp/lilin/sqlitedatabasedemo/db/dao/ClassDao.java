package com.cpp.lilin.sqlitedatabasedemo.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cpp.lilin.sqlitedatabasedemo.bean.Class;
import com.cpp.lilin.sqlitedatabasedemo.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Colin on 2017/4/22.
 */

public class ClassDao {

    private ClassDao() {
    }

    public static void insert(Context context, Class aClass) {
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("major", aClass.getMajor());
        database.insert(
                DatabaseHelper.TABLE_CLASSES,
                null,
                values
        );
        database.close();
    }

    public static void delete(Context context, int clsId) {
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        database.delete(
                DatabaseHelper.TABLE_CLASSES,
                "id=?",
                new String[]{String.valueOf(clsId)}
        );
        database.close();
    }

    public static void update(Context context, int clsId, String newMajor) {
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("major", newMajor);
        database.update(
                DatabaseHelper.TABLE_CLASSES,
                values,
                "id=?",
                new String[]{String.valueOf(clsId)}
        );
        database.close();
    }

    public static List<Class> getAll(Context context) {
        List<Class> classes = new ArrayList<>();
        SQLiteDatabase database1 = new DatabaseHelper(context).getWritableDatabase();
        Cursor cursor = database1.query(
                DatabaseHelper.TABLE_CLASSES,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                Class aClass = new Class();
                aClass.setId(cursor.getInt(0));
                aClass.setMajor(cursor.getString(1));
                classes.add(aClass);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database1.close();
        return classes;
    }
}
