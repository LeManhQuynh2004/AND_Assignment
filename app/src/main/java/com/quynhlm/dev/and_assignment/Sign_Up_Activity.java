package com.quynhlm.dev.and_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.quynhlm.dev.and_assignment.Dao.UserDao;
import com.quynhlm.dev.and_assignment.Model.User;

public class Sign_Up_Activity extends AppCompatActivity {

    EditText edtusername, edtpassword;

    Toolbar toolbar;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userDao = new UserDao(this);
        toolbar = findViewById(R.id.toolbar_dk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        edtusername = findViewById(R.id.edt_username_dk);
        edtpassword = findViewById(R.id.edt_password_dk);
        findViewById(R.id.btnSignUp).setOnClickListener(view -> {
            String username = edtusername.getText().toString().trim();
            String password = edtpassword.getText().toString().trim();
            if(username.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }else{
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                if (userDao.insertData(user)) {
                    Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Login_Activity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}