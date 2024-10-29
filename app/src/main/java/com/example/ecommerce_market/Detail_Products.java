package com.example.ecommerce_market;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.ecommerce_market.Domain.Item_Popular_Domain;
import com.example.ecommerce_market.databinding.ActivityDetailProductsBinding;

import java.text.NumberFormat;
import java.time.Instant;
import java.util.Locale;

public class Detail_Products extends AppCompatActivity {
    private Item_Popular_Domain Object;
    private ActivityDetailProductsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundle();
    }

    private void getBundle() {
       Object = (Item_Popular_Domain) getIntent().getSerializableExtra("Object");
       int drawableResourceId = this.getResources().getIdentifier(Object.getImage(), "drawable", this.getPackageName());
   /*    Glide.with(this).load(drawableResourceId).into(binding.imageView15);*/
        binding.textViewTitle.setText(Object.getTitle());
        binding.textViewPrices.setText(String.format("%s đ", NumberFormat.getInstance(new Locale("vi", "VN")).format(Object.getPrice())));
        binding.textViewRating.setText(Object.getRating()+"");
        binding.textViewReview.setText(Object.getRating());
        binding.btnBuy.setOnClickListener(v -> {

        });
    }
}