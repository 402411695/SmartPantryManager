package com.smartpantrymanager;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPantry;
    private PantryAdapter pantryAdapter;
    private PantryDAO pantryDAO;

    private Button buttonAddIngredient;

    private Button buttonViewRecipes;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonAddIngredient = findViewById(R.id.buttonAddIngredient);

        buttonAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        buttonViewRecipes = findViewById(R.id.buttonViewRecipes);

        buttonViewRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            startActivity(intent);
        });




        recyclerViewPantry = findViewById(R.id.recyclerViewPantry);

        pantryDAO = new PantryDAO(this);

        ArrayList<PantryItem> pantryItems = new ArrayList<>(pantryDAO.getAllPantryItems());

        recyclerViewPantry.setLayoutManager(new LinearLayoutManager(this));

        pantryAdapter = new PantryAdapter(pantryItems);

        recyclerViewPantry.setAdapter(pantryAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pantryDAO != null && recyclerViewPantry != null) {
            ArrayList<PantryItem> pantryItems = new ArrayList<>(pantryDAO.getAllPantryItems());

            pantryAdapter = new PantryAdapter(pantryItems);
            recyclerViewPantry.setAdapter(pantryAdapter);

        }
    }


}