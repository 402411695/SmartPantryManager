package com.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItems;

    //Constructor use to receive the pantry items
    public PantryAdapter(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    //  Holds the views for one pantry item
    public static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView textIngredientName;
        TextView textQuantity;
        TextView textExpiry;

        Button buttonDeleteIngredient;

        public PantryViewHolder(@NonNull View itemView) { super(itemView);
            textIngredientName = itemView.findViewById(R.id.textIngredientName);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            textExpiry = itemView.findViewById(R.id.textExpiry);
            buttonDeleteIngredient = itemView.findViewById(R.id.buttonDeleteIngredient);
        }
    }

    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {

        PantryItem item = pantryItems.get(position);

        holder.textIngredientName.setText(item.getName());
        holder.textQuantity.setText(item.getQuantity() + " " + item.getUnit());
        holder.textExpiry.setText(item.getExpiryDate());

        holder.buttonDeleteIngredient.setOnClickListener(v -> {
            PantryDAO pantryDAO = new PantryDAO(v.getContext());
            pantryDAO.deletePantryItem(item.getId());
            pantryItems.remove(position);
            notifyItemRemoved(position);

        });


    }


    @Override
    public int getItemCount() {
         return pantryItems.size();
    }
}