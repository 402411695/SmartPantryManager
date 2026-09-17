package com.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRecipes;
    private RecipeAdapter recipeAdapter;
    private RecipeDAO recipeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> {
            finish();
        });

        recyclerViewRecipes = findViewById(R.id.recyclerViewRecipes);

        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));

        recipeDAO = new RecipeDAO(this);

        List<Recipe> recipes = recipeDAO.getAllRecipes();

        recipeAdapter = new RecipeAdapter(recipes);

        recyclerViewRecipes.setAdapter(recipeAdapter);

    }
}