package com.example.librarymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DatabaseHelper db;
    ArrayList<String> book_ids, book_titles, book_authors, book_pages;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(MainActivity.this);
        book_ids = new ArrayList<>();
        book_titles = new ArrayList<>();
        book_authors = new ArrayList<>();
        book_pages = new ArrayList<>();

        fetchData();
        customAdapter = new CustomAdapter(this, this, book_ids, book_titles, book_authors, book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void fetchData() {
        Cursor cursor = db.getAllBooks();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                book_ids.add(cursor.getString(0));
                book_titles.add(cursor.getString(1));
                book_authors.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }
}