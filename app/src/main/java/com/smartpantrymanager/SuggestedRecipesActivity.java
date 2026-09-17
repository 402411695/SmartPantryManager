package com.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSuggestedRecipes;
    private TextView textNoSuggestedRecipes;

    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private PantryDAO pantryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> {
            finish();
        });

        recyclerViewSuggestedRecipes =
                findViewById(R.id.recyclerViewSuggestedRecipes);

        textNoSuggestedRecipes =
                findViewById(R.id.textNoSuggestedRecipes);

        recyclerViewSuggestedRecipes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recipeDAO = new RecipeDAO(this);
        recipeIngredientDAO = new RecipeIngredientDAO(this);
        pantryDAO = new PantryDAO(this);

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        List<Recipe> allRecipes = recipeDAO.getAllRecipes();
        List<PantryItem> pantryItems = pantryDAO.getAllPantryItems();

        ArrayList<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            List<RecipeIngredient> requiredIngredients =
                    recipeIngredientDAO.getIngredientsForRecipe(recipe.getId());

            boolean recipeMatches = true;

            for (RecipeIngredient requiredIngredient : requiredIngredients) {

                boolean ingredientAvailable = false;

                for (PantryItem pantryItem : pantryItems) {

                    String pantryName = normaliseName(pantryItem.getName());
                    String requiredName =
                            normaliseName(requiredIngredient.getIngredientName());

                    if (pantryName.equals(requiredName)
                            && pantryItem.getQuantity()
                            >= requiredIngredient.getRequiredQuantity()) {

                        ingredientAvailable = true;
                        break;
                    }
                }

                if (!ingredientAvailable) {
                    recipeMatches = false;
                    break;
                }
            }

            if (recipeMatches) {
                matchingRecipes.add(recipe);
            }
        }

        if (matchingRecipes.isEmpty()) {

            recyclerViewSuggestedRecipes.setVisibility(View.GONE);
            textNoSuggestedRecipes.setVisibility(View.VISIBLE);

        } else {

            recyclerViewSuggestedRecipes.setVisibility(View.VISIBLE);
            textNoSuggestedRecipes.setVisibility(View.GONE);

            RecipeAdapter recipeAdapter =
                    new RecipeAdapter(matchingRecipes);

            recyclerViewSuggestedRecipes.setAdapter(recipeAdapter);
        }
    }

    private String normaliseName(String name) {

        String normalised = name
                .trim()
                .toLowerCase(Locale.ROOT);

        if (normalised.endsWith("s")
                && !normalised.endsWith("ss")) {
            normalised = normalised.substring(
                    0,
                    normalised.length() - 1
            );
        }

        return normalised;
    }
}