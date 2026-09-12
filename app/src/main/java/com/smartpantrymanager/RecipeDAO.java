package com.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    private DatabaseHelper databaseHelper;

    public RecipeDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //Add a recipe
    public long addRecipe(Recipe recipe) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_RECIPE_NAME, recipe.getName());
        values.put(DatabaseHelper.COLUMN_RECIPE_INSTRUCTIONS, recipe.getInstructions());

        return db.insert(DatabaseHelper.TABLE_RECIPES, null, values);

    }

    //Get all recipes
    public List<Recipe> getAllRecipes() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Recipe> recipes = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.COLUMN_RECIPE_NAME + " ASC ");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_ID));

            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_NAME));

            String instructions = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INSTRUCTIONS));

            recipes.add(new Recipe(id, name, instructions));

        }

        cursor.close();

        return recipes;


    }
}
