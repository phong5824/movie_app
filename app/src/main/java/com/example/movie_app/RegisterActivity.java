package com.example.movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmailRegister;
    private EditText editTextPasswordRegister;
    private UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);

        userDBHelper = new UserDBHelper(this);

        Button btnRegister = findViewById(R.id.btnRegister);
        TextView textViewLogin = findViewById(R.id.textViewLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle register button click
                String email = editTextEmailRegister.getText().toString();
                String password = editTextPasswordRegister.getText().toString();

                // Kiểm tra xem các trường thông tin đã được nhập hay chưa
                if (email.trim().isEmpty() || password.trim().isEmpty()) {
                    // Nếu có một trong các trường chưa được nhập, hiển thị thông báo
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Thực hiện đăng ký nếu thông tin đã được nhập đầy đủ
                    addUserToDatabase(email, password);
                }
            }
        });


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close the current activity (RegisterActivity)
            }
        });
    }

    private void addUserToDatabase(String email, String password) {
        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu chưa
        if (userDBHelper.checkUser(email, password)) {
            Toast.makeText(this, "Đăng ký không thành công. Email đã tồn tại.", Toast.LENGTH_SHORT).show();
        } else {
            // Nếu email chưa tồn tại, thêm người dùng mới vào cơ sở dữ liệu
            User newUser = new User(email, password);
            userDBHelper.addUser(newUser);

            // Hiển thị thông báo đăng ký thành công và chuyển sang LoginActivity
            showRegistrationSuccessMessage();
        }
    }

    private void showRegistrationSuccessMessage() {
        // Display a success message
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

        // Navigate to LoginActivity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

        // Close the current activity to prevent the user from going back to RegisterActivity
        finish();
    }
}
