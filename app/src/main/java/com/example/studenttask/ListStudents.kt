package com.example.studenttask

//import adapter.StudentsAdapter
import android.app.ActionBar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_students.*
import pojo.Student

class ListStudents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students);



        val studentList=creatStudentList();
        //Toast.makeText(this,"asds",Toast.LENGTH_LONG).show();
        var linearLayoutManager = LinearLayoutManager(this);
        //listStudentsR.layoutManager=linearLayoutManager;
        //var adapter= StudentsAdapter(this, studentList);

        //listStudentsR.adapter=adapter;


    }

    private fun creatStudentList():List<Student>{
        var studentList=ArrayList<Student>();
        studentList.add(Student("张三", "123456"));
        studentList.add(Student("张四", "123456"));
        studentList.add(Student("张五", "123456"));
        studentList.add(Student("张六", "123456"));
        studentList.add(Student("张七", "123456"));
        return studentList;
    }
}