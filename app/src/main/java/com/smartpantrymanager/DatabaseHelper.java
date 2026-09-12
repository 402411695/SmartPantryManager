package com.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Helper class responsible for creating and managing the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {


    //Database name and version
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 3;

    //Pantry Table
    public static final String TABLE_PANTRY = "pantry_items";
    public static final String COLUMN_PANTRY_ID = "id";
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QUANTITY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";
    public static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    //Recipe Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    //Recipe Ingredients Table
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COLUMN_RECIPE_INGREDIENT_ID = "id";
    public static final String COLUMN_RECIPE_INGREDIENT_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RECIPE_INGREDIENT_NAME = "ingredient_name";
    public static final String COLUMN_RECIPE_INGREDIENT_QUANTITY = "required_quantity";
    public static final String COLUMN_RECIPE_INGREDIENT_UNIT = "unit";

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

        insertInitialRecipes(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

        onCreate(db);

    }

    private void insertInitialRecipes(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE_RECIPES +
                " (" + COLUMN_RECIPE_NAME + ", " + COLUMN_RECIPE_INSTRUCTIONS + ") VALUES " +
                "('Pasta with Tomato Sauce', 'Boil pasta. Prepare tomato sauce. Combine and serve.')," +
                "('Scrambled Eggs', 'Beat the eggs. Cook in a pan while stirring until done.')," +
                "('Cheese Sandwich', 'Place cheese between two slices of bread and toast or serve cold.')");
    }
}




