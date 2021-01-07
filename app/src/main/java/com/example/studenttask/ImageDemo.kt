package com.example.studenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image_demo.*

class ImageDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_demo)

        changImageViewButton.setOnClickListener{
            imageViewDemo.setImageResource(R.drawable.student_background);
        }
    }
}