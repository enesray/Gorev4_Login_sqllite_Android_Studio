package com.raytech.loginsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String veritabani_adi = "veritabani_musteri";
    private static final String users_tablosu = "tbl_users";
    private static final int veritabani_versiyon = 1;


    public Database(Context context) {
        super(context, veritabani_adi, null, veritabani_versiyon);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_userTablosuOlusturma = "create table " + users_tablosu + "(id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,userName TEXT,Password TEXT)";
        sqLiteDatabase.execSQL(sql_userTablosuOlusturma);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + users_tablosu);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", user.getName());
        cv.put("userName", user.getUserName());
        cv.put("Password", user.getPassword());

        long id = db.insert(users_tablosu, null, cv);
        return id;
    }

    public List<User> getirUserList() {
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSorgusu = "Select from" + users_tablosu;
        Cursor c = db.rawQuery(sqlSorgusu, null);
        int userId = c.getColumnIndex("ID");
        int nameNo = c.getColumnIndex("Name");
        int userName = c.getColumnIndex("userName");
        int userPassword = c.getColumnIndex("Password");
        try {
            while (c.moveToNext()) {
                User _user = new User();
                _user.setId(c.getInt(userId));
                _user.setName(c.getString(nameNo));
                _user.setUserName(c.getString(userName));
                _user.setPassword(c.getString(userPassword));
                userList.add(_user);
            }

        } finally {
            c.close();
            db.close();
        }

        return userList;


    }
}
