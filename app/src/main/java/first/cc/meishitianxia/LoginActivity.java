package first.cc.meishitianxia;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.Student;
import db.StudentDAO;

public class LoginActivity extends Activity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnGoRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");



        etUsername=(EditText)findViewById(R.id.et_username);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnGoRegister=(Button)findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goRegister();
            }
        });

    }


    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        StudentDAO studentDAO = new StudentDAO(this);
        Student student = studentDAO.find(username);
        if (student == null) {
            Toast.makeText(this, "不存在该用户！", Toast.LENGTH_SHORT).show();
        } else if (!student.password.equals(password)) {
            Toast.makeText(this, "密码错误，请输入正确的密码！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            MyApplication app = (MyApplication) getApplication();
            app.setStudent(student);
        }

    }

    /**
     * 去注册
     */
    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
