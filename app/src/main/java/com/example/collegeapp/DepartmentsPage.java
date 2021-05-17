package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsPage extends AppCompatActivity {

    private TextView CT,IT,Mech,Ele,Etc,Areo,Civil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments_page);


        CT = (TextView)findViewById(R.id.ct1);
        IT = (TextView)findViewById(R.id.it1);
        Mech = (TextView)findViewById(R.id.mech1);
        Ele = (TextView)findViewById(R.id.ele);
        Etc = (TextView)findViewById(R.id.etc);
        Areo = (TextView)findViewById(R.id.areo);
        Civil = (TextView)findViewById(R.id.civil);

        CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        IT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Areo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}