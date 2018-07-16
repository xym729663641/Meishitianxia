package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 44223 on 2018/7/12.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db";
    public static final int DB_VERSION = 1;
    private static final String STUDENT="t_student";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+STUDENT+"(name varchar(20) primary key,password varchar,email varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("StudentDAOTest", "UpGrade!");
    }
}
