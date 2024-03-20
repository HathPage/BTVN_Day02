package com.example.btvn_day02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText link_back_to_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editTextNewUsername = findViewById(R.id.et_name);
        EditText editTextNewPassword = findViewById(R.id.et_password);
        //EditText editTextConfirmPassword = findViewById(R.id.et_password);
        Button buttonRegisterNewUser = findViewById(R.id.btn_register);

        UserManagement userManager = new UserManagement(this);

        buttonRegisterNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = editTextNewUsername.getText().toString().trim();
                String newPassword = editTextNewPassword.getText().toString().trim();
                //String confirmPassword = editTextConfirmPassword.getText().toString().trim();
                if (newUsername.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Username must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newPassword.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if (!newPassword.equals(confirmPassword)) {
//                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if (userManager.registerUser(newUsername, newPassword)) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish(); // Kết thúc Activity và quay lại màn hình đăng nhập
                } else {
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView link_back = findViewById(R.id.link_back_to_login);
        link_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}