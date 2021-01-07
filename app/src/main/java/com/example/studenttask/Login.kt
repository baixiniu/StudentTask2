package com.example.studenttask

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import database.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_login.*
import pojo.Student2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import service.GetRequest_Interface


class Login : AppCompatActivity() {

    lateinit var loginButton: Button;
    lateinit var userName:String;
    lateinit var passWord:String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //登录成功后直接获取学生信息
        //先设置对象
        val retrofit = Retrofit.Builder()
            //.baseUrl("http://10.15.156.84:9100/")
            .baseUrl("http://10.17.108.14:9100/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//设置适配器
            .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
            .build()

        //创建 网络请求接口 的实例
        val myService = retrofit.create(GetRequest_Interface::class.java);


        val viewName:EditText= this.findViewById(R.id.LoginUsername);
        val passwordView:EditText=this.findViewById(R.id.LoginPassword);


        loginButton=this.findViewById(R.id.loginButton);
        var  students2=ArrayList<Student2>();
        loginButton.setOnClickListener{

            userName=viewName.text.toString();
            passWord= passwordView.text.toString();
            //Log.d("AAA",userName+","+passWord);

            //3.获取请求方法
            val method=myService.getApp(userName, passWord);


            //4.异步请求
            method.enqueue(object : Callback<List<Student2>> {
                //请求成功时回调
                override fun onResponse(
                    call: Call<List<Student2>>?,
                    response: Response<List<Student2>>?
                ) {
                    if (response != null) {
                        for (s: Student2 in response.body()) {
                            //val temp:Student2= Student2(s.stuNo,s.stuName,s.classes,s.gender,s.major,s.tel,s.dormNo,s.photoPath);
                            students2.add(s);
                            //Log.d("S",temp.toString());
                        }
                    };

                    Toast.makeText(this@Login, "登录成功", Toast.LENGTH_LONG).show();
                    /*val intent = Intent(this@Login, ListStudents2::class.java);
                    intent.putExtra("studentsList", students2);
                    intent.putExtra("username",userName);
                    intent.putExtra("password",passWord);*/

                    //数据库demo部分
                    /*var dbhelper: MyDatabaseHelper =
                        MyDatabaseHelper(this@Login, "userDB", null, 3);
                    val db: SQLiteDatabase = dbhelper.getWritableDatabase();
                    //把数据放入数据库
                    val values = ContentValues();
                    values.put("username",userName);
                    values.put("password",passWord);
                    db.insert("user",null,values);

                    this@Login.startActivity(intent);
                    //Log.d("students2",""+students2.size);*/

                    //用户名和密码就不使用sqlite了，上面只是一个小栗子
                    val sharedPreferences: SharedPreferences =this@Login.getSharedPreferences("user", MODE_PRIVATE);
                    val editor: SharedPreferences.Editor =  sharedPreferences.edit();
                    editor.putString("username",userName);
                    editor.putString("password",passWord);
                    editor.commit();
                    //Log.d("info",sharedPreferences.getString("username",""))

                    val intent = Intent(this@Login, ListStudents2::class.java);
                    intent.putExtra("studentsList", students2);
                    this@Login.startActivity(intent);
                }


                //失败时回调
                override fun onFailure(call: Call<List<Student2>>?, t: Throwable?) {
                    //Toast.makeText(this,"请求错误",Toast.LENGTH_LONG).show();
                    //请求失败对应很对情况，这里只考虑非请求成功的一种情况
                    Toast.makeText(this@Login, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    Log.d("result", "失败");
                    t?.printStackTrace()
                }

            })

            //method.execute();

        }


        /*val method2=myService.getApp2("kiki","123456");
        loginButton.setOnClickListener{
            var s:String="";
            method2.enqueue(object:Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    //Log.d("response", response?.body()?.string())
                    var students2:List<Student2>;
                    students2=response
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("result","失败");
                    t?.printStackTrace()
                }

            })
        }*/

        registButton.setOnClickListener{
            var intent:Intent= Intent(this, Regist::class.java);
            this.startActivity(intent);
        }
    }
}
