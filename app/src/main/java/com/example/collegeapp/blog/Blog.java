package com.example.collegeapp.blog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collegeapp.BlogAdapter;
import com.example.collegeapp.BlogData;
import com.example.collegeapp.BlogRequested;
import com.example.collegeapp.BlogRequestedAdapter;
import com.example.collegeapp.CreateBlog;
import com.example.collegeapp.R;
import com.example.collegeapp.WhatIsBlog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Blog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Blog extends Fragment {

    private Button CB , WIB;
    private ImageView RB;
    private List<BlogData> list;
    private RecyclerView myBlog;

    private BlogAdapter adapter;

    private DatabaseReference reference , dbRef;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Blog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Blog.
     */
    // TODO: Rename and change types and number of parameters
    public static Blog newInstance(String param1, String param2) {
        Blog fragment = new Blog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blog, container, false);

        CB = (Button)view.findViewById(R.id.createBlog);
        WIB = (Button)view.findViewById(R.id.WhatIsBlog);
        RB = (ImageView)view.findViewById(R.id.requestedBlog);

        myBlog = (RecyclerView)view.findViewById(R.id.myBlogU);
        reference = FirebaseDatabase.getInstance().getReference().child("BlogAccepted");

        myBlogR();

        CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateBlog.class);
                startActivity(intent);
            }
        });

        WIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WhatIsBlog.class);
                startActivity(intent);
            }
        });
        RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BlogRequested.class);
                startActivity(intent);
            }
        });

        return (view);
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
                myBlog.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new BlogAdapter(list,getActivity());
                myBlog.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}