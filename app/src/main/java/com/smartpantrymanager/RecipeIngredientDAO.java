package com.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDAO {

    private DatabaseHelper databaseHelper;

    public RecipeIngredientDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //Add a recipe ingredient
    public long addRecipeIngredient(RecipeIngredient ingredient) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_RECIPE_ID, ingredient.getRecipeId());
        values.put(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_NAME, ingredient.getIngredientName());
        values.put(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_QUANTITY, ingredient.getRequiredQuantity());
        values.put(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_UNIT, ingredient.getUnit());

        return db.insert(DatabaseHelper.TABLE_RECIPE_INGREDIENTS, null, values);

    }

    //Get ingredients for a recipe
    public List<RecipeIngredient> getIngredientsForRecipe(int recipeId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<RecipeIngredient> ingredients = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_RECIPE_INGREDIENTS,
                null,
                DatabaseHelper.COLUMN_RECIPE_INGREDIENT_RECIPE_ID + " = ?", new String[] {String.valueOf(recipeId)},
                null,
                null,
                DatabaseHelper.COLUMN_RECIPE_INGREDIENT_NAME + " ASC ");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_ID));

            int storedRecipeId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_RECIPE_ID));

            String ingredientName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_NAME));

            double requiredQuantity = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_QUANTITY));

            String unit = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_INGREDIENT_UNIT));

            ingredients.add(new RecipeIngredient(id, storedRecipeId, ingredientName, requiredQuantity, unit));

        }

        cursor.close();

        return ingredients;


    }
}


