package com.example.ecommerce_market.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Ecommerce.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        String CREATE_USERS_TABLE = "CREATE TABLE Users ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT, "
                + "password TEXT, "
                + "email TEXT)";

        // Tạo bảng Products
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE Products ("
                + "product_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "description TEXT, "
                + "price REAL)";

        // Tạo bảng Orders
        String CREATE_ORDERS_TABLE = "CREATE TABLE Orders ("
                + "order_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER, "
                + "order_date TEXT, "
                + "FOREIGN KEY (user_id) REFERENCES Users(user_id))";

        // Tạo bảng order_detail
        String CREATE_ORDER_DETAIL_TABLE = "CREATE TABLE order_detail ("
                + "order_item_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "order_id INTEGER, "
                + "product_id INTEGER, "
                + "quantity INTEGER, "
                + "FOREIGN KEY (order_id) REFERENCES Orders(order_id), "
                + "FOREIGN KEY (product_id) REFERENCES Products(product_id))";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_ORDER_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS order_detail");
        onCreate(db);
    }

    // Phương thức thêm người dùng
    public void addUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", hashPassword(password)); // Mã hóa mật khẩu
        values.put("email", email);
        db.insert("Users", null, values);
        db.close();
    }

    // Phương thức kiểm tra thông tin đăng nhập
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hashedPassword = hashPassword(password); // Mã hóa mật khẩu nhập vào
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[]{username, hashedPassword});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Hàm mã hóa mật khẩu bằng SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
