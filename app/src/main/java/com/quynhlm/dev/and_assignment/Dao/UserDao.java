package com.quynhlm.dev.and_assignment.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.and_assignment.Database.Db_Helper;
import com.quynhlm.dev.and_assignment.Model.User;

import java.util.ArrayList;

public class UserDao {
    Db_Helper db_helper;

    public UserDao(Context context) {
        db_helper = new Db_Helper(context);
    }

    public boolean insertData(User user) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        long check = sqLiteDatabase.insert("Users", null, contentValues);
        return check > 0;
    }

    public boolean updateData(User user) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(user.getUsername())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", user.getPassword());
        long check = sqLiteDatabase.update("Users", contentValues, "username=?", dk);
        return check > 0;
    }

    public boolean updatePassword(String username, String Password) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", Password);
        String[] dk = new String[]{username};
        long check = sqLiteDatabase.update("Users", contentValues, "username=?", dk);
        return check > 0;
    }


    public boolean deleteData(User user) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(user.getUsername())};
        long check = sqLiteDatabase.delete("Users", "username=?", dk);
        return check > 0;
    }

    public ArrayList<User> selectAll() {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new User(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        String[] selectionArgs = new String[]{username, password};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", selectionArgs);
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String username) {
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        String[] selectionArgs = new String[]{username};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE username = ?", selectionArgs);
        return cursor.getCount() > 0;
    }
}
