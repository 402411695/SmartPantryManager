package com.smartpantrymanager;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;

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

    private TextView textAddEditTitle;
    private int ingredientId = -1;


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
        textAddEditTitle = findViewById(R.id.textAddEditTitle);

        Intent intent = getIntent();
        ingredientId = intent.getIntExtra("ingredient_id", -1);

        if (ingredientId == -1) {
            textAddEditTitle.setText("Add Ingredient");
        } else {
            textAddEditTitle.setText("Edit Ingredient");


        }

        if (ingredientId != -1) {
            editIngredientName.setText(intent.getStringExtra("ingredient_name"));
            editIngredientQuantity.setText(String.valueOf(intent.getDoubleExtra("ingredient_quantity", 0)));
            editIngredientUnit.setText(intent.getStringExtra("ingredient_unit"));
            editIngredientExpiry.setText(intent.getStringExtra("ingredient_expiry"));
        }

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

            PantryItem item = new PantryItem(ingredientId, name, quantity, unit, expiryDate);

            PantryDAO pantryDAO = new PantryDAO(this);

            if (ingredientId == -1) {
                long result = pantryDAO.addPantryItem(item);


                if (result != -1) {
                    Toast.makeText(this, "Ingredient saved", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(this, "Could not save ingredient", Toast.LENGTH_SHORT).show();

                }

            } else {
                int result = pantryDAO.updatePantryItem(item);

                if (result > 0) {
                    Toast.makeText(this, "Ingredient updated", Toast.LENGTH_SHORT).show();

                    new android.os.Handler().postDelayed(() -> {
                        finish();

                    }, 1000);


                } else {
                    Toast.makeText(this, "Could not update ingredient", Toast.LENGTH_SHORT).show();

                }
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}