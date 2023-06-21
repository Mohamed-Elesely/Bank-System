// DatabaseHelper.java

package com.example.banksystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_CARD_NUMBER = "cardNumber";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PIN_KEY = "pinKey";
    private static final String COLUMN_BALANCE = "balance";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_CARD_NUMBER + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_PIN_KEY + " TEXT, " +
                COLUMN_BALANCE + " REAL DEFAULT 0)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }

    public void insertUser(UserModel user) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CARD_NUMBER, user.getCardNumber());
            values.put(COLUMN_NAME, user.getName());
            values.put(COLUMN_EMAIL, user.getEmail());
            values.put(COLUMN_PASSWORD, user.getPassword());
            values.put(COLUMN_PIN_KEY, user.getPinKey());
            values.put(COLUMN_BALANCE, user.getBalance());

            db.insert(TABLE_USERS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception here, e.g., log the error or show an error message
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public boolean isValidKeyCombination(String cardNumber, String pinKey) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = COLUMN_CARD_NUMBER + " = ? AND " +
                COLUMN_PIN_KEY + " = ?";
        String[] selectionArgs = {cardNumber, pinKey};

        Cursor cursor = db.query(TABLE_USERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        boolean isValid = cursor != null && cursor.moveToFirst();

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return isValid;
    }

    @SuppressLint("Range")
    public double getBalance(String cardNumber) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = COLUMN_CARD_NUMBER + " = ?";
        String[] selectionArgs = {cardNumber};

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_BALANCE},
                selection,
                selectionArgs,
                null,
                null,
                null);

        double balance = 0;

        if (cursor != null && cursor.moveToFirst()) {
            balance = cursor.getDouble(cursor.getColumnIndex(COLUMN_BALANCE));
            cursor.close();
        }

        db.close();

        return balance;
    }

    public void updateBalance(String cardNumber, double balance) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BALANCE, balance);

        String whereClause = COLUMN_CARD_NUMBER + " = ?";
        String[] whereArgs = {cardNumber};

        db.update(TABLE_USERS, values, whereClause, whereArgs);

        db.close();
    }
}
