package pojo

open class Student constructor(name:String,password:String) {
    //由于后续涉及到修改密码等，设置为var
    private lateinit var studentName:String;
    private lateinit var password:String;

    init{
        this.studentName=name;
        this.password=password;
    }

    public fun getStudentName():String{
        return this.studentName;
    }

    public fun getStudentPassword():String{
        return this.password;
    }
}