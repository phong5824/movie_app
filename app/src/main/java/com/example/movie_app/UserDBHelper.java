package com.example.movie_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL + " TEXT UNIQUE NOT NULL,"
                + COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // CREATE
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        long id = -1;

        try {
            id = db.insertOrThrow(TABLE_USERS, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return id;
    }

    // READ
    public User getUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_ID + "=?",
                new String[]{String.valueOf(userId)}, null, null, null, null);

        User user = null;

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
                    int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);

                    if (idIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                        user = new User();
                        user.setId(cursor.getLong(idIndex));
                        user.setEmail(cursor.getString(emailIndex));
                        user.setPassword(cursor.getString(passwordIndex));
                    }
                }
            } finally {
                cursor.close();
            }
        }

        db.close();
        return user;
    }

    // UPDATE
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        int rowsAffected = -1;

        try {
            rowsAffected = db.update(TABLE_USERS, values, COLUMN_ID + "=?",
                    new String[]{String.valueOf(user.getId())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return rowsAffected;
    }

    // DELETE
    public void deleteUser(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_USERS, null, null, null, null, null, null);

            if (cursor != null) {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
                int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);

                while (cursor.moveToNext()) {
                    if (idIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                        User user = new User(
                                cursor.getLong(idIndex),
                                cursor.getString(emailIndex),
                                cursor.getString(passwordIndex)
                        );
                        userList.add(user);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return userList;
    }

    // CHECK USER
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_USERS, null,
                    COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                    new String[]{email, password}, null, null, null);

            return cursor != null && cursor.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return false;
    }

}

