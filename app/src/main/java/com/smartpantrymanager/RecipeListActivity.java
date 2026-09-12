package com.smartpantrymanager;

import android.os.Bundle;

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

        recyclerViewRecipes = findViewById(R.id.recyclerViewRecipes);

        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));

        recipeDAO = new RecipeDAO(this);

        List<Recipe> recipes = recipeDAO.getAllRecipes();

        recipeAdapter = new RecipeAdapter(recipes);

        recyclerViewRecipes.setAdapter(recipeAdapter);

    }
}