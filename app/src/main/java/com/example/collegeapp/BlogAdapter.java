package com.example.collegeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewAdapter>{

    private List<BlogData> list;
    private Context context;
    public String[] ItemList;

    private StorageReference storageReference;
    private DatabaseReference databaseReference , dbRef;

    public BlogAdapter(List<BlogData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BlogAdapter.BlogViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_blog, parent, false);
        return new BlogAdapter.BlogViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.BlogViewAdapter holder, int position) {
        BlogData item = list.get(position);
        holder.blogData.setText(item.getBlogData());
        holder.date.setText(item.getCurrentDate());
        Picasso.get().load(item.getBlogPic()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogViewAdapter extends RecyclerView.ViewHolder
    {
        private TextView blogData,date;
        private ImageView image;

        public BlogViewAdapter(@NonNull View itemView)
        {
            super(itemView);
            blogData = itemView.findViewById(R.id.blogDes);
            date = itemView.findViewById(R.id.currentDateB);
            image = itemView.findViewById(R.id.blogP);
        }

    }
}
