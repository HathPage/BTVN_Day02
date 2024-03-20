package com.example.btvn_day02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText editTextUsername, editTextPassword;
    protected Button buttonLogin, buttonRegister;
    protected TextView tvRegister;
    protected UserManagement userManagement;
    private boolean checkbox = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userManagement = new UserManagement(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        editTextUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextUsername.setBackgroundResource(R.drawable.shape_focus);
                } else {
                    editTextUsername.setBackgroundResource(R.drawable.shape); // Khôi phục khung màu ban đầu
                }
            }
        });
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextPassword.setBackgroundResource(R.drawable.shape_focus);
                } else {
                    editTextPassword.setBackgroundResource(R.drawable.shape); // Khôi phục khung màu ban đầu
                }
            }
        });


        final ImageButton togglePasswordVisibility = findViewById(R.id.eye);

        // Thiết lập sự kiện cho ImageButton
        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPassword.getInputType() == 129) { //129 là kiểu password (ẩn pass)
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    togglePasswordVisibility.setImageResource(R.drawable.eye_open); // Thay đổi icon khi hiển thị mật khẩu

                } else {
                    editTextPassword.setInputType(129);
                    togglePasswordVisibility.setImageResource(R.drawable.eyeicon); // Thay đổi icon khi ẩn mật khẩu
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (userManagement.loginUser(username, password)) {
                    Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        tvRegister = findViewById(R.id.buttonRegister);
//        tvRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
        ImageButton remember = findViewById(R.id.remember_me);
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox){
                    remember.setBackgroundResource(R.drawable.checkboxlight);
                    checkbox = false;
                } else {
                    remember.setBackgroundResource(R.drawable.checkboxdark);
                    checkbox = true;
                }
            }
        });

        TextView tvRegister = findViewById(R.id.buttonRegister);
        String text = "Don't have an account? Create Account";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Thực hiện hành động khi "Create Account" được bấm
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };

        // Thiết lập clickable span cho phần "Create Account"
        spannableString.setSpan(clickableSpan,
                text.indexOf("Create Account"), text.length(), 0);

        // Thiết lập spannableString cho TextView
        tvRegister.setText(spannableString);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }
}