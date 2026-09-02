package com.smartpantrymanager;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
public class PantryDAO {
    //Provides access to the SQLite database
    private final DatabaseHelper databaseHelper;

    //Constructor used ot initialize the database helper
    public PantryDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //Adds a new pantry item to the database
    public long addPantryItem(PantryItem item) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PANTRY_NAME, item.getName());
        values.put(DatabaseHelper.COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(DatabaseHelper.COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(DatabaseHelper.COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

        return db.insert(DatabaseHelper.TABLE_PANTRY, null, values);

    }

    //Retrieve all pantry items from the database
    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> pantryItems = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_PANTRY,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PANTRY_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PANTRY_NAME));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PANTRY_QUANTITY));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PANTRY_UNIT));
            String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PANTRY_EXPIRY));

            PantryItem item = new PantryItem(id, name, quantity, unit, expiryDate);

            pantryItems.add(item);


        }

        cursor.close();

        return pantryItems;
    }

    //Updates an existing pantry item in the database
    public int updatePantryItem(PantryItem item) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PANTRY_NAME, item.getName());
        values.put(DatabaseHelper.COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(DatabaseHelper.COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(DatabaseHelper.COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

        return db.update(
                DatabaseHelper.TABLE_PANTRY,
                values,
                DatabaseHelper.COLUMN_PANTRY_ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );
    }

    //Deletes a pantry item from the database
    public int deletePantryItem(int id) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        return db.delete(
                DatabaseHelper.TABLE_PANTRY,
                DatabaseHelper.COLUMN_PANTRY_ID + " = ?",
                new String[]{String.valueOf(id)}
        );


    }

}






