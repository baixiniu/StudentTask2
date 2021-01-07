package com.example.studenttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_judge_student.*

class JudgeStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_judge_student)

        alertInfo.setOnClickListener{
            val resultCode:Int=1;
            //获取从另一个activity传过来的intent
            var intent:Intent=getIntent();
            var username:String=intent.getStringExtra("username");
            var password:String=intent.getStringExtra("password");
            Toast.makeText(this,username+","+password,Toast.LENGTH_LONG).show();

            val intent2= Intent(this,Regist::class.java);
            //这里将方向反转，以便跳回Regist
            intent2.putExtra("result","success");
            this.setResult(resultCode,intent2);
            //使用此函数Activity想要返回的数据返回到父Activity
            finish();

        }
    }
}