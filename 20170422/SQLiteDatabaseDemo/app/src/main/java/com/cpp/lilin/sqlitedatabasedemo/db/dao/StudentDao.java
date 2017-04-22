package com.cpp.lilin.sqlitedatabasedemo.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cpp.lilin.sqlitedatabasedemo.bean.Student;
import com.cpp.lilin.sqlitedatabasedemo.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Colin on 2017/4/22.
 */

public class StudentDao {

    private StudentDao() {
    }

    public static void insert(Context context, Student student) {
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("tel", student.getTel());
        values.put("cls_id", student.getClsId());
        database.insert(
                DatabaseHelper.TABLE_STUDENTS,
                null,
                values
        );
        database.close();
    }

    public static List<Student> getAll(Context context) {
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        List<Student> students = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_STUDENTS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setClsId(cursor.getInt(2));
                students.add(student);
            } while (cursor.moveToNext());
        }

        return students;
    }
}
