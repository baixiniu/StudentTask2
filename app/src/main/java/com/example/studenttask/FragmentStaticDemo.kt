package com.example.studenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.left_fragment.*
import kotlinx.android.synthetic.main.right_fragment.*

class FragmentStaticDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_static_demo)

        buttonInLeftFragment.setOnClickListener{
            buttonInLeftFragment.setText("按钮1");
            Toast.makeText(this,"我是左边fragment的按钮",Toast.LENGTH_SHORT).show();
        }

        buttonInRightFragment.setOnClickListener{
            buttonInRightFragment.setText("按钮2");
            Toast.makeText(this,"我是右边fragment的按钮",Toast.LENGTH_SHORT).show();
        }
    }
}