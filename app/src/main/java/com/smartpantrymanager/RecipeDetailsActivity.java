package com.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        TextView textRecipeDetailsName = findViewById(R.id.textRecipeDetailsName);
        TextView textRecipeDetailsIngredients = findViewById(R.id.textRecipeDetailsIngredients);
        TextView textRecipeDetailsInstructions = findViewById(R.id.textRecipeDetailsInstructions);

        Button buttonBackToRecipes = findViewById(R.id.buttonBackToRecipes);
        buttonBackToRecipes.setOnClickListener(view -> finish());

        int recipeId = getIntent().getIntExtra("recipe_id", -1);

        String recipeName = getIntent().getStringExtra("recipe_name");
        String recipeInstructions = getIntent().getStringExtra("recipe_instructions");

        textRecipeDetailsName.setText(recipeName);
        textRecipeDetailsInstructions.setText(recipeInstructions);

        RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO(this);

        List<RecipeIngredient> ingredients = recipeIngredientDAO.getIngredientsForRecipe(recipeId);

        StringBuilder ingredientText = new StringBuilder();
        ingredientText.append("Ingredients:\n");

        for (RecipeIngredient ingredient : ingredients) {
            ingredientText.append("- ")
                    .append(ingredient.getIngredientName())
                    .append(": ")
                    .append(ingredient.getRequiredQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        textRecipeDetailsIngredients.setText(ingredientText.toString());
    }

}
