package com.quynhlm.dev.and_assignment.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.and_assignment.Database.Db_Helper;
import com.quynhlm.dev.and_assignment.Model.Product;

import java.util.ArrayList;

public class ProductDao {
    Db_Helper db_helper;

    public ProductDao(Context context) {
        db_helper = new Db_Helper(context);
    }

    public boolean insertData(Product product) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("quantity", product.getQuantity());
        long check = sqLiteDatabase.insert("Products", null, contentValues);
        product.setProduct_id((int) check);
        return check > 0;
    }

    public boolean updateData(Product product) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(product.getProduct_id())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("quantity", product.getQuantity());
        long check = sqLiteDatabase.update("Products", contentValues, "product_id=?", dk);
        return check > 0;
    }

    public boolean deleteData(Product product) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(product.getProduct_id())};
        long check = sqLiteDatabase.delete("Products", "product_id=?", dk);
        return check > 0;
    }

    public ArrayList<Product> selectAll() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Products", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Product> select_price_ASC() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Products ORDER BY price ASC", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Product> select_Price_DESC() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Products ORDER BY price DESC", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Product> select_name_ASC() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Products ORDER BY name ASC", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Product> select_name_DESC() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Products ORDER BY name DESC", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
