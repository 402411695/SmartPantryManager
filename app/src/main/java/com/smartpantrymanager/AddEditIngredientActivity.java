package com.smartpantrymanager;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEditIngredientActivity extends AppCompatActivity {
    private EditText editIngredientName;
    private EditText editIngredientQuantity;
    private EditText editIngredientUnit;
    private EditText editIngredientExpiry;
    private Button buttonSaveIngredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_ingredient);
        editIngredientName = findViewById(R.id.editIngredientName);
        editIngredientQuantity = findViewById(R.id.editIngredientQuantity);
        editIngredientUnit = findViewById(R.id.editIngredientUnit);
        editIngredientExpiry = findViewById(R.id.editIngredientExpiry);
        buttonSaveIngredient = findViewById(R.id.buttonSaveIngredient);

        buttonSaveIngredient.setOnClickListener(v -> {
            String name = editIngredientName.getText().toString().trim();
            String quantityText = editIngredientQuantity.getText().toString().trim();
            String unit = editIngredientUnit.getText().toString().trim();
            String expiryDate = editIngredientExpiry.getText().toString().trim();

            if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                Toast.makeText(this, "Please complete the required fields", Toast.LENGTH_SHORT).show();
                        return;

            }

            double quantity = Double.parseDouble(quantityText);

            PantryItem item = new PantryItem(0, name, quantity, unit, expiryDate);

            PantryDAO pantryDAO = new PantryDAO(this);

            long result = pantryDAO.addPantryItem(item);

            if (result != -1) {
                Toast.makeText(this, "Ingredient saved", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "Could not save ingredient", Toast.LENGTH_SHORT).show();

            }


        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}