package com.example.movie_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        TextView textViewRegister = findViewById(R.id.textViewRegister);

        // Initialize your UserDBHelper
        userDBHelper = new UserDBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle login button click
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Kiểm tra xem các trường thông tin đã được nhập hay chưa
                if (email.trim().isEmpty() || password.trim().isEmpty()) {
                    // Nếu có một trong các trường chưa được nhập, hiển thị thông báo
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện đăng nhập nếu thông tin đã được nhập đầy đủ
                    // Check user credentials in the database
                    if (userDBHelper.checkUser(email, password)) {
                        // If credentials are correct, proceed with login success
                        showLoginSuccessMessage("Đăng nhập thành công");
                    } else {
                        // If credentials are incorrect, show an error message
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công. Kiểm tra lại thông tin đăng nhập.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to the RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showLoginSuccessMessage(String token) {
        // Lưu token vào SharedPreferences
        saveAuthToken(token);

        // Hiển thị thông báo đăng nhập thành công và chuyển sang MainActivity
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        // Đóng LoginActivity để ngăn người dùng quay lại
        finish();
    }

    private void saveAuthToken(String token) {
        // Sử dụng SharedPreferences để lưu trữ token
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", token);
        editor.apply();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        if (userDBHelper != null) {
            userDBHelper.close();
        }
    }
}
