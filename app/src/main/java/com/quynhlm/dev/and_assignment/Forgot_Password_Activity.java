package com.quynhlm.dev.and_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quynhlm.dev.and_assignment.Dao.UserDao;
import com.quynhlm.dev.and_assignment.Model.User;

import java.util.Random;

public class Forgot_Password_Activity extends AppCompatActivity {

    EditText edt_check_user, edt_captcha;
    TextView txt_captcha;
    UserDao userDao;
    EditText edt_password, edt_refresh;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        toolbar = findViewById(R.id.toolbar_resetpassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userDao = new UserDao(this);
        edt_check_user = findViewById(R.id.edt_check_username);
        edt_captcha = findViewById(R.id.edt_captcha);
        txt_captcha = findViewById(R.id.txt_captcha);
        txt_captcha.setText("Nf4d8");
        findViewById(R.id.img_refresh).setOnClickListener(view -> {
            Random random = new Random();
            txt_captcha.setText(random.nextInt(5000) + "");
        });
        findViewById(R.id.btnCheck_User).setOnClickListener(view -> {
            String username = edt_check_user.getText().toString().trim();
            String input_captcha = edt_captcha.getText().toString().trim();
            String captcha = txt_captcha.getText().toString().trim();

            if (userDao.checkUser(username) && input_captcha.equals(captcha)) {
                Toast.makeText(this, "Tài khoản đúng", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.forgot_pass_word, null);
                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                edt_password = view1.findViewById(R.id.edt_forget_password);
                edt_refresh = view1.findViewById(R.id.edt_refresh_password);

                view1.findViewById(R.id.btnSubmit).setOnClickListener(view2 -> {
                    String password = edt_password.getText().toString().trim();
                    String refresh = edt_refresh.getText().toString().trim();
                    if (password.isEmpty() || !password.equals(refresh)) {
                        Toast.makeText(this, "Mật khẩu không trùng lặp hoặc bỏ trống", Toast.LENGTH_SHORT).show();
                    } else {
                        if (userDao.updatePassword(username, password)) {
                            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                        finish();
                    }
                });
                view1.findViewById(R.id.btnCancel).setOnClickListener(view2 -> {
                    alertDialog.dismiss();
                    finish();
                });
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            } else {
                Toast.makeText(this, input_captcha.equals(captcha) ? "Không tìm thấy tài khoản" : "Nhập Sai Mã Captcha", Toast.LENGTH_SHORT).show();
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