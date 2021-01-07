package pojo

import java.io.Serializable


class Student2 :Serializable {
    var stuNo: String? = null
        private set
    var stuName: String? = null
        private set
    var classes: String? = null
        private set
    var gender: String? = null
        private set
    var major: String? = null
        private set
    var tel: String? = null
        private set
    var dormNo: String? = null
        private set
    var photoPath: String? = null
        private set

    constructor(
        stuNo: String?,
        stuName: String?,
        classes: String?,
        gender: String?,
        major: String?,
        tel: String?,
        dormNo: String?,
        photoPath: String?
    ) {
        this.stuName = stuName
        this.stuNo = stuNo
        this.classes = classes
        this.gender = gender
        this.major = major
        this.tel = tel
        this.dormNo = dormNo
        this.photoPath = photoPath
    }

    constructor() {}

    override fun toString(): String {
        return "Student{" + "stuNo='" + stuNo + '\'' +
                "," + "stuName='" + stuName + '\'' +
                "," + "classes='" + classes + '\'' +
                "," + "gender='" + gender + '\'' +
                "," + "major='" + major + '\'' +
                "," + "tel='" + tel + '\'' +
                "," + "dormNo='" + dormNo + '\'' +
                "," + "photoPath='" + photoPath + '\'' +
                "}"
    }
}
