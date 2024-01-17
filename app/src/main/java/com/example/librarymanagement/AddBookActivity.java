package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {
    EditText title, author, pages;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        title = findViewById(R.id.title_input);
        author = findViewById(R.id.author_input);
        pages = findViewById(R.id.pages_input);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDatabase = new DatabaseHelper(AddBookActivity.this);
                long result = myDatabase.addBook(title.getText().toString().trim(), author.getText().toString().trim(), Integer.valueOf(pages.getText().toString().trim()));
                if (result == -1)
                    Toast.makeText(AddBookActivity.this, "Failed", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddBookActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}