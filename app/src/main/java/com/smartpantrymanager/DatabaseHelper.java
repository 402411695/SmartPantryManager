package com.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Helper class responsible for creating and managing the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {


    //Database name and version
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 6;

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
                "('Cheese Sandwich', 'Place cheese between two slices of bread and toast or serve cold.')," +
                "('Chicken Salad', 'Cook the chicken. Chop the vegetables. Mix everything together.')," +
                "('Vegetable Stir-Fry', 'Chop the vegetables. Stir-fry them in oil until tender.')," +
                "('Tuna Sandwich', 'Mix tuna with mayonnaise. Place the mixture between slices of bread.')," +
                "('Omelette', 'Beat the eggs. Add the vegetables and cook in a pan.')," +
                "('Rice and Beans', 'Cook the rice. Heat the beans. Mix and serve.')");


        db.execSQL("INSERT INTO " + TABLE_RECIPE_INGREDIENTS + " (" + COLUMN_RECIPE_INGREDIENT_RECIPE_ID + ", " +
                COLUMN_RECIPE_INGREDIENT_NAME + ", " +
                COLUMN_RECIPE_INGREDIENT_QUANTITY + ", " +
                COLUMN_RECIPE_INGREDIENT_UNIT + ") VALUES " +
                "(1, 'pasta', 200, 'g')," +
                "(1, 'tomato', 2, 'whole')," +
                "(1, 'salt', 1, 'tsp')," +
                "(1, 'oil', 1, 'tbsp')," +

                "(2, 'eggs', 2, 'whole')," +
                "(2, 'salt', 1, 'tsp')," +
                "(2, 'oil', 1, 'tbsp')," +

                "(3, 'bread', 2, 'slices')," +
                "(3, 'cheese', 2, 'slices')," +

                "(4, 'chicken', 200, 'g')," +
                "(4, 'lettuce', 1, 'whole')," +
                "(4, 'tomato', 1, 'whole')," +
                "(4, 'cucumber', 1, 'whole')," +

                "(5, 'carrot', 1, 'whole')," +
                "(5, 'broccoli', 100, 'g')," +
                "(5, 'pepper', 1, 'whole')," +
                "(5, 'oil', 1, 'tbsp')," +

                "(6, 'tuna', 1, 'can')," +
                "(6, 'bread', 2, 'slices')," +
                "(6, 'mayonnaise', 1, 'tbsp')," +

                "(7, 'eggs', 2, 'whole')," +
                "(7, 'tomato', 1, 'whole')," +
                "(7, 'onion', 1, 'whole')," +
                "(7, 'oil', 1, 'tbsp')," +

                "(8, 'rice', 200, 'g')," +
                "(8, 'beans', 1, 'can')," +
                "(8, 'salt', 1, 'tsp');");




    }
}




