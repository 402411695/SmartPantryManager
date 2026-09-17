package com.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class RecipeDetailsActivity extends AppCompatActivity {

    private TextView textRecipeName;
    private TextView textRecipeIngredients;
    private TextView textRecipePreparation;
    private Button buttonBack;

    private RecipeIngredientDAO recipeIngredientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);

        // Connect Java variables to the XML IDs
        textRecipeName = findViewById(R.id.textRecipeName);
        textRecipeIngredients = findViewById(R.id.textRecipeIngredients);
        textRecipePreparation = findViewById(R.id.textRecipePreparation);
        buttonBack = findViewById(R.id.buttonBack);

        // Create the ingredient DAO
        recipeIngredientDAO = new RecipeIngredientDAO(this);

        // Retrieve the values sent by RecipeAdapter
        String recipeName =
                getIntent().getStringExtra("recipe_name");

        String recipeInstructions =
                getIntent().getStringExtra("recipe_instructions");

        int recipeId =
                getIntent().getIntExtra("recipe_id", -1);

        // Display the recipe name
        if (recipeName != null && !recipeName.isEmpty()) {
            textRecipeName.setText(recipeName);
        } else {
            textRecipeName.setText("Recipe");
        }

        // Display the preparation instructions
        if (recipeInstructions != null && !recipeInstructions.isEmpty()) {
            textRecipePreparation.setText(recipeInstructions);
        } else {
            textRecipePreparation.setText(
                    "No preparation instructions available."
            );
        }

        // Retrieve and display the ingredients from SQLite
        if (recipeId != -1) {
            displayIngredients(recipeId);
        } else {
            textRecipeIngredients.setText(
                    "No ingredients available."
            );
        }

        // Return to the previous screen
        buttonBack.setOnClickListener(view -> finish());
    }

    private void displayIngredients(int recipeId) {

        List<RecipeIngredient> ingredients =
                recipeIngredientDAO.getIngredientsForRecipe(recipeId);

        if (ingredients == null || ingredients.isEmpty()) {
            textRecipeIngredients.setText(
                    "No ingredients available."
            );
            return;
        }

        StringBuilder ingredientsText = new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {

            String quantity = String.format(
                    Locale.getDefault(),
                    "%.2f",
                    ingredient.getRequiredQuantity()
            );

            ingredientsText
                    .append("• ")
                    .append(ingredient.getIngredientName())
                    .append(" - ")
                    .append(quantity)
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        textRecipeIngredients.setText(
                ingredientsText.toString().trim()
        );
    }
}