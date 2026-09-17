package com.smartpantrymanager;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPantry;
    private PantryAdapter pantryAdapter;
    private PantryDAO pantryDAO;
    private ArrayList<PantryItem> pantryItems;

    private Button buttonAddIngredient;
    private Button buttonViewRecipes;
    private Button buttonSuggestedRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonAddIngredient = findViewById(R.id.buttonAddIngredient);
        buttonViewRecipes = findViewById(R.id.buttonViewRecipes);
        buttonSuggestedRecipes = findViewById(R.id.buttonSuggestedRecipes);
        recyclerViewPantry = findViewById(R.id.recyclerViewPantry);

        buttonAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddEditIngredientActivity.class
            );
            startActivity(intent);
        });

        buttonViewRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    RecipeListActivity.class
            );
            startActivity(intent);
        });

        buttonSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });

        pantryDAO = new PantryDAO(this);

        pantryItems = new ArrayList<>();

        recyclerViewPantry.setLayoutManager(
                new LinearLayoutManager(this)
        );

        pantryAdapter = new PantryAdapter(pantryItems);

        recyclerViewPantry.setAdapter(pantryAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> insets
        );


        loadPantryItems();
    }

    private void loadPantryItems() {
        ArrayList<PantryItem> updatedItems =
                new ArrayList<>(pantryDAO.getAllPantryItems());

        pantryItems.clear();
        pantryItems.addAll(updatedItems);

        pantryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pantryDAO != null && pantryAdapter != null) {
            loadPantryItems();
        }
    }
}