package com.example.ecommerce_market.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_market.Domain.Item_Popular_Domain;
import com.example.ecommerce_market.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Item_Popular_Adapter extends RecyclerView.Adapter<Item_Popular_Adapter.Viewholder> {
    private ArrayList<Item_Popular_Domain> items;
    private Context context;

    public Item_Popular_Adapter(Context context, ArrayList<Item_Popular_Domain> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Item_Popular_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho item
        View view = LayoutInflater.from(context).inflate(R.layout.activity_category, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_Popular_Adapter.Viewholder holder, int position) {
        Item_Popular_Domain currentItem = items.get(position);

        // Gán dữ liệu cho view
        holder.titleTextView.setText(currentItem.getTitle());
        holder.priceTextView.setText(String.format("%s đ", NumberFormat.getInstance(new Locale("vi", "VN")).format(currentItem.getPrice())));
        holder.ratingTextView.setText(currentItem.getRating());

        // Nếu bạn đang sử dụng hình ảnh từ drawable resources
        int imageResId = context.getResources().getIdentifier(currentItem.getImage(), "drawable", context.getPackageName());
        holder.imageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return items.size(); // Trả về số lượng items trong danh sách
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView;
        TextView ratingTextView;
        TextView locationTextView;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            ratingTextView = itemView.findViewById(R.id.textViewRating);
            locationTextView = itemView.findViewById(R.id.textViewLocation);
        }
    }
}
