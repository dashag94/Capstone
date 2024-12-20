package com.example.projectthree;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryItem> itemList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        dbHelper = new DatabaseHelper(this);
        itemList = dbHelper.getAllItems(); // Ensure getAllItems() is implemented in DatabaseHelper
        inventoryAdapter = new InventoryAdapter(itemList); // Ensure InventoryAdapter class is implemented
        recyclerView.setAdapter(inventoryAdapter);
    }
}
