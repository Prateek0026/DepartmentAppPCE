package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogRequested extends AppCompatActivity {

    private List<BlogData> list;
    private RecyclerView myBlog;

    private BlogRequestedAdapter adapter;

    private DatabaseReference reference , dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_requested);

        myBlog = (RecyclerView)findViewById(R.id.myRequestedBlogs);
        reference = FirebaseDatabase.getInstance().getReference().child("Blog");

        myBlogR();
    }

    private void myBlogR()
    {
        dbRef = reference.child("Index");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                list = new ArrayList<>();
                myBlog.setVisibility(View.VISIBLE);
                for(DataSnapshot datasnapshot: snapshot.getChildren())
                {
                    BlogData data = datasnapshot.getValue(BlogData.class);
                    list.add(data);
                }
                myBlog.setHasFixedSize(true);
                myBlog.setLayoutManager(new LinearLayoutManager(BlogRequested.this));
                adapter = new BlogRequestedAdapter(list,BlogRequested.this);
                myBlog.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(BlogRequested.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}