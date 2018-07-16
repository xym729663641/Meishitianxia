package first.cc.meishitianxia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.Student;
import db.StudentDAO;

public class RegisterActivity extends Activity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnGoLogin;
    private EditText etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etemail=findViewById(R.id.et_email);
        btnGoLogin = findViewById(R.id.btn_gologin);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gologin();
            }
        });

        btnRegister=findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etemail.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();
        }

        // 先判断改用户信息是否已经被注册
        StudentDAO studentDAO = new StudentDAO(this);
        Student student = studentDAO.find(username);
        if (student != null) {
            Toast.makeText(this, "该用户名已经被注册！", Toast.LENGTH_SHORT).show();
        } else {
            student = new Student(username, password,email);
            studentDAO.add(student);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

            // 注册成功后，跳转登录页面让用户进行登录
            gologin();
        }

    }


    private void gologin(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
