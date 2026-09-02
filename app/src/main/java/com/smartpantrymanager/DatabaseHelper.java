package com.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Helper class responsible for creating and managing the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {


    //Database name and version
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    //Pantry Table
    private static final String TABLE_PANTRY = "pantry_items";
    private static final String COLUMN_PANTRY_ID = "id";
    private static final String COLUMN_PANTRY_NAME = "name";
    private static final String COLUMN_PANTRY_QUANTITY = "quantity";
    private static final String COLUMN_PANTRY_UNIT = "unit";
    private static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    //Recipe Table
    private static final String TABLE_RECIPES = "recipes";
    private static final String COLUMN_RECIPE_ID = "id";
    private static final String COLUMN_RECIPE_NAME = "name";
    private static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    //Recipe Ingredients Table
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    private static final String COLUMN_RECIPE_INGREDIENT_ID = "id";
    private static final String COLUMN_RECIPE_INGREDIENT_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_INGREDIENT_NAME = "ingredient_name";
    private static final String COLUMN_RECIPE_INGREDIENT_QUANTITY = "required_quantity";
    private static final String COLUMN_RECIPE_INGREDIENT_UNIT = "unit";

    //Constructor used to initialise the database helper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create pantry table
        String createPantryTable = " CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PANTRY_NAME + " TEXT NOT NULL, " +
                COLUMN_PANTRY_QUANTITY + " REAL NOT NULL, " +
                COLUMN_PANTRY_UNIT + " TEXT NOT NULL, " +
                COLUMN_PANTRY_EXPIRY + " TEXT" + ")";
        db.execSQL(createPantryTable);

        //Create recipes table
        String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INSTRUCTIONS + " TEXT NOT NULL " + ")";

        db.execSQL(createRecipesTable);

        //Create recipe ingredients table
        String createRecipeIngredientsTable = " CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                COLUMN_RECIPE_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_INGREDIENT_RECIPE_ID + " INTEGER NOT NULL, " +
                COLUMN_RECIPE_INGREDIENT_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INGREDIENT_QUANTITY + " REAL NOT NULL, " +
                COLUMN_RECIPE_INGREDIENT_UNIT + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_RECIPE_INGREDIENT_RECIPE_ID + ") REFERENCES " + TABLE_RECIPES + "(" + COLUMN_RECIPE_ID + ")" + ")";

        db.execSQL(createRecipeIngredientsTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

        onCreate(db);

    }
}




