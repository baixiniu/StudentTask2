package com.example.studenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_student_info_modify.*
import pojo.Student2

class StudentInfoModify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info_modify)

        var intentFromListStudents2=intent;
        var student:Student2=intentFromListStudents2.getSerializableExtra("student") as Student2;

        stuname.setText(student.stuName);
        stuNo.setText(student.stuNo);
        stumajor.setText(student.major);
        stutel.setText(student.tel);
        stuclass.setText(student.classes);
        stugender.setText(student.gender);
        studoor.setText(student.dormNo);
        if(student.gender.equals("男")){
            stuHead.setImageResource(R.drawable.boy);
        }else{
            stuHead.setImageResource(R.drawable.girl);
        }
    }
}