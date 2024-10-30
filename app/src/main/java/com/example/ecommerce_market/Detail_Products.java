package com.example.ecommerce_market;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce_market.Domain.Item_Popular_Domain;
import com.example.ecommerce_market.R;

import java.text.NumberFormat;
import java.util.Locale;

public class Detail_Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        // Tham chiếu các thành phần
        TextView titleTextView = findViewById(R.id.textViewTitle);
        TextView priceTextView = findViewById(R.id.textViewPrices);
        TextView ratingTextView = findViewById(R.id.textViewRating);
        TextView descriptionTextView = findViewById(R.id.descriptionTxt);
        ImageView productImageView = findViewById(R.id.imageProduct);
        ImageView backButton = findViewById(R.id.ImageBack);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        double price = intent.getDoubleExtra("price", 0);
        String rating = intent.getStringExtra("rating");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        String imageBack = intent.getStringExtra("imageBack");

        // Gán dữ liệu vào các thành phần
        titleTextView.setText(title);
        priceTextView.setText(String.format("%s đ", NumberFormat.getInstance(new Locale("vi", "VN")).format(price)));
        ratingTextView.setText(rating);
        descriptionTextView.setText(description);

        // Hiển thị hình ảnh sản phẩm
        int imageResId = getResources().getIdentifier(image, "drawable", getPackageName());
        productImageView.setImageResource(imageResId);

        // Xử lý sự kiện khi nhấn nút back
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}
