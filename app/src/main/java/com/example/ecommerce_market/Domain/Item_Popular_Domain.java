package com.example.ecommerce_market.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Item_Popular_Domain implements Parcelable {
    private String title;
    private double price;
    private String image; // Đường dẫn hoặc tên tài nguyên hình ảnh
    private String rating;
    private double score;
    private String location;

    // Constructor
    public Item_Popular_Domain(String title, double price, String image, String rating, double score, String location) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.rating = rating;
        this.score = score;
        this.location = location;
    }

    // Getters và Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Implement Parcelable
    protected Item_Popular_Domain(Parcel in) {
        title = in.readString();
        price = in.readDouble();
        image = in.readString();
        rating = in.readString();
        score = in.readDouble();
        location = in.readString();
    }

    public static final Creator<Item_Popular_Domain> CREATOR = new Creator<Item_Popular_Domain>() {
        @Override
        public Item_Popular_Domain createFromParcel(Parcel in) {
            return new Item_Popular_Domain(in);
        }

        @Override
        public Item_Popular_Domain[] newArray(int size) {
            return new Item_Popular_Domain[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeDouble(price);
        dest.writeString(image);
        dest.writeString(rating);
        dest.writeDouble(score);
        dest.writeString(location);
    }
}
