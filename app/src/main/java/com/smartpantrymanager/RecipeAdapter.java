package com.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

    //Constructor use to receive the recipes
    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    //  Holds the views for one recipe
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView textRecipeName;
        TextView textRecipeInstructions;

        public RecipeViewHolder(@NonNull View itemView) { super(itemView);
            textRecipeName = itemView.findViewById(R.id.textRecipeName);
            textRecipeInstructions = itemView.findViewById(R.id.textRecipeInstructions);

        }
    }

    @NonNull
    @Override

    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Recipe recipe = recipes.get(position);

        holder.textRecipeName.setText(recipe.getName());
        holder.textRecipeInstructions.setText(recipe.getInstructions());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}

