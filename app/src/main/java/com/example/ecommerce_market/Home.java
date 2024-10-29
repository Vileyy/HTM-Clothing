package com.example.ecommerce_market;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommerce_market.Adapter.Item_Popular_Adapter;
import com.example.ecommerce_market.Domain.Item_Popular_Domain;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Item_Popular_Adapter itemAdapter;
    private ArrayList<Item_Popular_Domain> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Thiết lập RecyclerView
        setupRecyclerView();

        // Thiết lập Window Insets
        setupWindowInsets(findViewById(R.id.main));
    }

    private void setupRecyclerView() {
        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts); // Đảm bảo ID này đúng
        // Thiết lập LinearLayoutManager với hướng ngang
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Khởi tạo danh sách sản phẩm
        itemList = new ArrayList<>();
        loadItems(); // Tải dữ liệu sản phẩm vào danh sách

        // Tạo adapter và gán cho RecyclerView
        itemAdapter = new Item_Popular_Adapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
    }

    private void loadItems() {
        // Thêm các sản phẩm vào danh sách
        itemList.add(new Item_Popular_Domain("T-Shirt NOW", 10000, "item_1", "4.8 | 3.6k đã bán", 4.8,"TP. Hồ Chí Minh"));
        itemList.add(new Item_Popular_Domain("Stylish Hat", 50000, "item_2", "4.5 | 2.1k đã bán", 4.5 ,"TP. Hồ Chí Minh"));
        itemList.add(new Item_Popular_Domain("Classic Sneakers", 80000, "item_3", "4.9 | 5.3k đã bán", 4.9,"Bình Dương"));
        itemList.add(new Item_Popular_Domain("Monitor Samsung", 10000, "item_4", "4.8 | 3.6k đã bán", 4.8,"Sài Gòn"));

    }

    private void setupWindowInsets(View mainView) {
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}
