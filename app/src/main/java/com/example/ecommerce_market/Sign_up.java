package com.example.ecommerce_market;

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

public class Sign_up extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPass;
    private Button btnSignUp;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        // Khởi tạo các trường EditText và Button
        editTextUsername = findViewById(R.id.editText_Username);
        editTextEmail = findViewById(R.id.editText_Email);
        editTextPassword = findViewById(R.id.editText_Password);
        editTextConfirmPass = findViewById(R.id.editText_ConfrimPass);
        btnSignUp = findViewById(R.id.btn_signup);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Thiết lập sự kiện cho nút đăng ký
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPass.getText().toString().trim();

                // Kiểm tra xem người dùng đã nhập đủ thông tin chưa
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return; // Dừng lại nếu có trường rỗng
                }

                // Kiểm tra xem mật khẩu có khớp không
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Sign_up.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return; // Dừng lại nếu mật khẩu không khớp
                }

                // Thêm người dùng vào cơ sở dữ liệu
                dbHelper.addUser(username, password, email);
                Toast.makeText(Sign_up.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();

                // Kết thúc Activity đăng ký
                finish();
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
