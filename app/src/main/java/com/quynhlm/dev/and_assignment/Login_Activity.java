package com.quynhlm.dev.and_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quynhlm.dev.and_assignment.Dao.UserDao;

import org.w3c.dom.Text;

public class Login_Activity extends AppCompatActivity {

    EditText edtusername, edtpassword;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDao = new UserDao(this);
        edtusername = findViewById(R.id.edt_username_dn);
        edtpassword = findViewById(R.id.edt_password_dn);

        findViewById(R.id.txt_chuyendendangky).setOnClickListener(view -> {
            startActivity(new Intent(this, Sign_Up_Activity.class));
        });
        findViewById(R.id.txt_reset_password).setOnClickListener(view -> {
            startActivity(new Intent(this, Forgot_Password_Activity.class));
        });
        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            String username = edtusername.getText().toString();
            String password = edtpassword.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if (userDao.checkLogin(username, password)) {
                    Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}