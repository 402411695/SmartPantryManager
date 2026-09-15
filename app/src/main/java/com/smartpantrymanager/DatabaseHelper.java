package com.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Helper class responsible for creating and managing the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {


    //Database name and version
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 12;

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
                "('Rice and Beans', 'Cook the rice. Heat the beans. Mix and serve.')," +
                "('Baked Potatoes', 'Bake the potatoes until soft. Season and serve.'), " +
                "('French Toast', 'Dip the bread in beaten eggs and fry until golden brown.')," +
                "('Chicken Wrap', 'Cook the chicken. Place the chicken and vegetables inside a wrap and serve.')," +
                "('Vegetable Pasta', 'Boil the pasta. Cook the vegetables and mix them with the pasta.'),"
+               "('Tomato Soup', 'Cook the tomatoes with onion and water. Blend until smooth and season.')," +
                "('Vegetable Rice Bowl', 'Cook the rice. Add cooked vegetables and mix together. Serve warm.')," +
                "('Tuna Pasta', 'Cook the pasta. Mix with tuna and mayonnaise, then serve.')");


        db.execSQL("INSERT INTO " + TABLE_RECIPE_INGREDIENTS + " (" + COLUMN_RECIPE_INGREDIENT_RECIPE_ID + ", " +
                COLUMN_RECIPE_INGREDIENT_NAME + ", " +
                COLUMN_RECIPE_INGREDIENT_QUANTITY + ", " +
                COLUMN_RECIPE_INGREDIENT_UNIT + ") VALUES " +
                //Recipe 1:  Pasta with tomato sauce
                "(1, 'pasta', 200, 'g')," +
                "(1, 'tomato', 2, 'whole')," +
                "(1, 'salt', 1, 'tsp')," +
                "(1, 'oil', 1, 'tbsp')," +

                //Recipe 2:  Scrambled eggs
                "(2, 'eggs', 2, 'whole')," +
                "(2, 'salt', 1, 'tsp')," +
                "(2, 'oil', 1, 'tbsp')," +

                //Recipe 3:  Cheese Sandwich
                "(3, 'bread', 2, 'slices')," +
                "(3, 'cheese', 2, 'slices')," +

                //Recipe 4:  Chicken Salad
                "(4, 'chicken', 200, 'g')," +
                "(4, 'lettuce', 1, 'whole')," +
                "(4, 'tomato', 1, 'whole')," +
                "(4, 'cucumber', 1, 'whole')," +

                //Recipe 5:  Vegetable Stirfry
                "(5, 'carrot', 1, 'whole')," +
                "(5, 'broccoli', 100, 'g')," +
                "(5, 'pepper', 1, 'whole')," +
                "(5, 'oil', 1, 'tbsp')," +

                //Recipe 6:  Tuna Sandwich
                "(6, 'tuna', 1, 'can')," +
                "(6, 'bread', 2, 'slices')," +
                "(6, 'mayonnaise', 1, 'tbsp')," +

                //Recipe 7:  Omelette
                "(7, 'eggs', 2, 'whole')," +
                "(7, 'tomato', 1, 'whole')," +
                "(7, 'onion', 1, 'whole')," +
                "(7, 'oil', 1, 'tbsp')," +

                //Recipe 8:  Rice and Beans
                "(8, 'rice', 200, 'g')," +
                "(8, 'beans', 1, 'can')," +
                "(8, 'salt', 1, 'tsp')," +

                //Recipe 9:  Baked Potatoes
                "(9, 'potatoes', 2, 'whole')," +
                "(9, 'oil', 1, 'tbsp')," +

                //Recipe 10:  French Toast
                "(10, 'bread', 2, 'slices')," +
                "(10, 'eggs', 2, 'whole')," +
                "(10, 'milk', 100, 'ml')," +
                "(10, 'oil', 1, 'tbsp')," +

                //Recipe 11:  Chicken Wrap
                "(11, 'chicken', 150, 'g')," +
                "(11, 'wrap', 1, 'whole')," +
                "(11, 'lettuce', 30, 'g')," +

                //Recipe 12:  Vegetable Pasta
                "(12, 'pasta', 200, 'g')," +
                "(12, 'mixed vegetables', 150, 'g')," +

                //Recipe 13:  Tomato Soup
                "(13, 'tomato', 3, 'whole')," +
                "(13, 'water', 250, 'ml')," +

                //Recipe 14:  Vegetable Rice Bowl
                "(14, 'rice', 200, 'g')," +
                "(14, 'mixed vegetables', 150, 'g')," +

                //Recipe 15:  Tuna Pasta
                "(15, 'tuna', 1, 'can')," +
                "(15, 'pasta', 200, 'g')");


    }
}




