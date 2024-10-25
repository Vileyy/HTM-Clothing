package com.example.ecommerce_market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ecommerce_market.database.DatabaseHelper;

public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login); // Đảm bảo layout chính xác

        // Khởi tạo các trường EditText và Button
        editTextUsername = findViewById(R.id.editText_Username);
        editTextPassword = findViewById(R.id.editText_Password);
        btnLogin = findViewById(R.id.btn_login);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Thiết lập sự kiện cho nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Kiểm tra xem người dùng đã nhập đủ thông tin chưa
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return; // Dừng lại nếu có trường rỗng
                }

                // Kiểm tra thông tin đăng nhập
                if (dbHelper.checkUser(username, password)) {
                    // Đăng nhập thành công
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    // Chuyển đến MainActivity hoặc màn hình chính
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish(); // Kết thúc Activity đăng nhập
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(Login.this, "Tài khoản đăng nhập không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Áp dụng Padding cho hệ thống bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
