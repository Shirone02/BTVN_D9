package com.example.btvn_d9.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.btvn_d9.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WishlistDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="product.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String PRODUCT_TITLE = "title";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_RATING = "rating";
    public static final String PRODUCT_IMAGES = "images";


    public WishlistDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + PRODUCT_TITLE + " TEXT NOT NULL PRIMARY KEY,"
                + PRODUCT_RATING + " TEXT NOT NULL,"
                + PRODUCT_PRICE + " TEXT NOT NULL,"
                + PRODUCT_IMAGES + " TEXT)";
        db.execSQL(sql);
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @SuppressLint("Range")
    public List<Product> getProducts(){
        List<Product> listProducts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE)));
                product.setPrice(Integer.valueOf(cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE))));
                product.setRating(Double.valueOf(cursor.getString(cursor.getColumnIndex(PRODUCT_RATING))));

                Gson gson = new Gson();
                Type typeToken =new TypeToken<List<String>>(){}.getType();
                List<String> data = gson.fromJson(cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGES)), typeToken);

                product.setImages(data);
                listProducts.add(product);
            }
        }
        return listProducts;
    }
}
