package com.example.projectthree;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private final List<InventoryItem> itemList;
    private final OnItemClickListener itemClickListener;

    // Interface for handling item click events
    public interface OnItemClickListener {
        void onItemClick(InventoryItem item);
    }

    // Constructor that accepts a list of items and a click listener
    public InventoryAdapter(List<InventoryItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each row in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current InventoryItem from the list
        InventoryItem item = itemList.get(position);

        // Check for null to ensure data safety
        if (item != null) {
            // Set the name and quantity in the corresponding TextViews
            holder.nameTextView.setText(item.getName());
            holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

            // Set click listener for each item
            holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(item));
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the itemList
        return itemList == null ? 0 : itemList.size();
    }

    // ViewHolder class to hold references to each item's views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare the TextView variables for name and quantity
        public final TextView nameTextView;
        public final TextView quantityTextView;

        // Constructor for ViewHolder
        public ViewHolder(View view) {
            super(view);
            // Initialize the TextView variables using findViewById
            nameTextView = view.findViewById(R.id.nameTextView);
            quantityTextView = view.findViewById(R.id.quantityTextView);
        }
    }
}
