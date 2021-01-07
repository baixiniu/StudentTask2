package adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studenttask.ListStudents2
import com.example.studenttask.R
import com.example.studenttask.StudentInfoModify
import pojo.OnRecyclerViewClickListener
import pojo.Student2

class StudentsAdapter2 : RecyclerView.Adapter<StudentsAdapter2.MyHolder>{
    lateinit var context:Context;
    lateinit var studentsList:List<Student2>;

    constructor(context: Context,students:List<Student2>){
        this.context=context;
        studentsList=students;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsAdapter2.MyHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.student2_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var temp:Student2=studentsList[position];
        holder.classesText.setText(temp.classes);
        holder.doorText.setText(temp.dormNo);
        holder.genderText.setText(temp.gender);
        holder.stunameText.setText(temp.stuName);
        holder.telText.setText(temp.tel);
        holder.majorText.setText(temp.major);
        holder.stunoText.setText(temp.stuNo);
        if(temp.gender.equals("男"))
            holder.imageView.setImageResource(R.drawable.boy);
        else
            holder.imageView.setImageResource(R.drawable.girl);

        holder.itemView.setOnClickListener{
            var intentToStudentModify=Intent(context,StudentInfoModify::class.java);
            intentToStudentModify.putExtra("student",temp);
            context.startActivity(intentToStudentModify);
        }
    }

    override fun getItemCount(): Int {
        return studentsList.size;
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stunoText:TextView=itemView.findViewById(R.id.stunoText);
        var stunameText:TextView=itemView.findViewById(R.id.stunameText);
        var classesText:TextView=itemView.findViewById(R.id.classText);
        var genderText:TextView=itemView.findViewById(R.id.genderText);
        var majorText:TextView=itemView.findViewById(R.id.majorText);
        val telText:TextView=itemView.findViewById(R.id.telText);
        val doorText:TextView=itemView.findViewById(R.id.doorText);
        val imageView:ImageView=itemView.findViewById(R.id.stuPhoto);


    }


}