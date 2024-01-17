package com.example.librarymanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;

    Activity activity;
    private ArrayList book_ids, book_titles, book_authors, book_pages;

    CustomAdapter(Activity activity, Context context, ArrayList book_ids, ArrayList book_titles, ArrayList book_authors, ArrayList book_pages) {
        this.activity = activity;
        this.context = context;
        this.book_ids = book_ids;
        this.book_titles = book_titles;
        this.book_authors = book_authors;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.book_id.setText(String.valueOf(book_ids.get(position)));
        holder.book_title.setText(String.valueOf(book_titles.get(position)));
        holder.book_author.setText(String.valueOf(book_authors.get(position)));
        holder.book_pages.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(book_ids.get(position)));
                intent.putExtra("title",String.valueOf(book_titles.get(position)));
                intent.putExtra("author",String.valueOf(book_authors.get(position)));
                intent.putExtra("pages",String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_ids.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView book_id, book_title, book_author, book_pages;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.book_id);
            book_title = itemView.findViewById(R.id.book_title);
            book_author = itemView.findViewById(R.id.book_author);
            book_pages = itemView.findViewById(R.id.book_pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
