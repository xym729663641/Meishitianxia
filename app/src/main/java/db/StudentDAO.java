package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import data.Student;

/**
 * Created by 44223 on 2018/7/12.
 */

public class StudentDAO {
    private DBHelper helper;
    private SQLiteDatabase db;
    public StudentDAO(Context context){
        helper=new DBHelper(context);
    }

    /*添加学生信息***
    ***
     */
    public void add(Student student){
        db=helper.getWritableDatabase();
        db.execSQL("insert into t_student (name ,password ,email) values (?,?,?)",new Object[]
                {
                        student.getName(),student.password,student.getEmail()});
    }

    public void update(Student student){
        db=helper.getWritableDatabase();
        db.execSQL(" update t_student set email=?,password=? where name=? ", new Object[]
                {student.password,student.getEmail(),student.getName()}
        );
    }

    /*查找学生信息**
    ***
     */

    public Student find(String name) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, password, email from t_student where name =?", new String[] {name});
        if (cursor.moveToNext()) {
            return new Student(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("password")), cursor.getString(cursor.getColumnIndex("email")));
        }
        return null;
    }

}
