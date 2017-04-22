package com.cpp.lilin.sqlitedatabasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cpp.lilin.sqlitedatabasedemo.bean.Class;
import com.cpp.lilin.sqlitedatabasedemo.bean.Student;
import com.cpp.lilin.sqlitedatabasedemo.db.dao.ClassDao;
import com.cpp.lilin.sqlitedatabasedemo.db.dao.StudentDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_cls_insert).setOnClickListener(this);
        findViewById(R.id.btn_cls_update).setOnClickListener(this);
        findViewById(R.id.btn_cls_delete).setOnClickListener(this);
        findViewById(R.id.btn_cls_select).setOnClickListener(this);
        findViewById(R.id.btn_stu_insert).setOnClickListener(this);
        findViewById(R.id.btn_stu_select).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_cls_insert:
                ClassDao.insert(this, new Class("English"));
                ClassDao.insert(this, new Class("Chinese"));
                break;
            case R.id.btn_cls_update:
                ClassDao.update(this, 1, "Hello world");
                break;
            case R.id.btn_cls_delete:
                ClassDao.delete(this, 1);
                break;
            case R.id.btn_cls_select:
                List<Class> classes = ClassDao.getAll(this);
                for (Class aClass : classes) {
                    Log.e(TAG, "onClick: " + aClass.toString());
                }
                break;
            case R.id.btn_stu_insert:
                StudentDao.insert(this, new Student("Colin", "110", 1));
                break;
            case R.id.btn_stu_select:
                List<Student> students = StudentDao.getAll(this);
                for (Student student : students) {
                    Log.e(TAG, "onClick: " + student.toString());
                }
                break;
        }

    }

    private static final String TAG = "MainActivity";
}
