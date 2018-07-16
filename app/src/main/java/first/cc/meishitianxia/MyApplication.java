package first.cc.meishitianxia;

import android.app.Application;

import data.Student;

/**
 * Created by 44223 on 2018/7/15.
 */

public class MyApplication extends Application {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
