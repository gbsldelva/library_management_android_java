package com.example.librarymanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input, author_input, pages_input;
    String id, title, author, pages;
    Button update_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title_input = findViewById(R.id.title_input_update);
        author_input = findViewById(R.id.author_input_update);
        pages_input = findViewById(R.id.pages_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                long result = db.updateBook(id, title_input.getText().toString().trim(), author_input.getText().toString().trim(), pages.toString().trim());
                if(result == -1) {
                    Toast.makeText(UpdateActivity.this, "Failed to update", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Get the data
            this.id = getIntent().getStringExtra("id");
            this.title = getIntent().getStringExtra("title");
            this.author = getIntent().getStringExtra("author");
            this.pages = getIntent().getStringExtra("pages");

            //Set the data
            title_input.setText(this.title);
            author_input.setText(this.author);
            pages_input.setText(this.pages);
        } else {
            Toast.makeText(UpdateActivity.this, "No data passed", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                long result = db.removeBook(id);
                if(result == -1) {
                    Toast.makeText(UpdateActivity.this, "Failed to delete", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}