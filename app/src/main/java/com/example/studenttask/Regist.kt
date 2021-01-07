package com.example.studenttask

import android.app.Activity
import android.app.AppComponentFactory
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.ActionBar
//import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.regist.*
import pojo.Student2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import service.GetRequest_Interface

class Regist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.regist)


        val registButton:Button= findViewById(R.id.registButton);
        val loginButton:Button=findViewById(R.id.loginButton);

        registButton.setOnClickListener{
            val textView:TextView=findViewById(R.id.registUsername);
            val username= textView.text.toString();
            val password:String=registPassword.text.toString();

            //这里使用Sharedpreference存储用户名和密码，不再使用sqlite
            //Log.d("path",this.filesDir.path);
            val sharedPreferences:SharedPreferences=this.getSharedPreferences("user", MODE_PRIVATE);
            val editor:SharedPreferences.Editor =  sharedPreferences.edit();
            editor.putString("username",username);
            editor.putString("password",password);
            editor.commit();
            Log.d("path",sharedPreferences.getString("username",""))

            if(!password.equals(registPassword2.text.toString())){
                Toast.makeText(this, "密码不一致", Toast.LENGTH_LONG).show();
                Log.d("password1", password.toString());
                Log.d("password2", registPassword2.text.toString())
            }
            else
            {
                //Toast.makeText(this, password, Toast.LENGTH_LONG).show();
                //var url:URL =URL("loaclhost:80/?username="+username+"&password="+password+"");
                /*val requestCode:Int=1
                var intent = Intent(this, JudgeStudent::class.java);
                //由于我还没有跑后端，这里直接采用intent传数据
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                this.startActivityForResult(intent,requestCode);*/
                //这个方法只发送数据，不会获得返回值，要想处理返回值需要处理onActivityResult()方法,这个方法在activity中
                //需要改变继承类
                //this.startActivity(intent);

                val retrofit = Retrofit.Builder()
                    //.baseUrl("http://10.15.156.84:9100/")
                    .baseUrl("http://10.17.108.14:9100/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//设置适配器
                    .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                    .build()
                val myService = retrofit.create(GetRequest_Interface::class.java);

                val method=myService.registResult(username,password);

                var  students2=ArrayList<Student2>();

                method.enqueue(object : Callback<List<Student2>> {
                    //请求成功时回调
                    override fun onResponse(call: Call<List<Student2>>?, response: Response<List<Student2>>?) {
                        if (response != null) {
                            for (s: Student2 in response.body()) {
                                //val temp:Student2= Student2(s.stuNo,s.stuName,s.classes,s.gender,s.major,s.tel,s.dormNo,s.photoPath);
                                students2.add(s);
                                //Log.d("S",temp.toString());
                            }
                        };

                        Toast.makeText(this@Regist, "注册成功", Toast.LENGTH_LONG).show();
                        val intent = Intent(this@Regist, ListStudents2::class.java);
                        intent.putExtra("studentsList", students2);
                        this@Regist.startActivity(intent);
                        Log.d("students2", "" + students2.size);
                    }
                    override fun onFailure(call: Call<List<Student2>>?, t: Throwable?) {
                        Toast.makeText(this@Regist,"注册失败",Toast.LENGTH_SHORT).show();
                        Log.d("result","失败");
                        t?.printStackTrace()
                    }
                });
            }
        }

        loginButton.setOnClickListener{
            //跳转到登录页面，在这个页面中其实也不用进行跳转，只是为了学习，单独写一个activity
            val intent=Intent(this,Login::class.java);
            this.startActivity(intent);
        }
    }

    //@Override
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==requestCode)
        {
            var result:String= data.getStringExtra("result") ;20
            if(result.equals("success")){
                val intentToListStudents=Intent(this,ListStudents::class.java);
                this.startActivity(intentToListStudents);
            }
                //Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        }
    }*/


}