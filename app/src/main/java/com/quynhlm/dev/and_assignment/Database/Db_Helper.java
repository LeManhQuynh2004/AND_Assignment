package com.quynhlm.dev.and_assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_Helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "assignment";
    private static final int DATABASE_VERSION = 1;

    public Db_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_user = "CREATE TABLE Users(" +
                "username TEXT NOT NULL PRIMARY KEY," +
                "password TEXT NOT NULL)";
        sqLiteDatabase.execSQL(create_table_user);
        String create_table_product = "CREATE TABLE Products(" +
                "product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price INTEGER NOT NULL," +
                "quantity INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(create_table_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");
        onCreate(sqLiteDatabase);
    }
}
