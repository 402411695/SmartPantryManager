package com.smartpantrymanager;

import android.os.Bundle;
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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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
}