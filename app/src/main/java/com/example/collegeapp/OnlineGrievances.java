package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class OnlineGrievances extends AppCompatActivity {

    private Spinner UserType,Department,Gender,IncidenceTime;
    String categoryUT,categoryD,categoryG,categoryIT;
    private Button SubmitB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_grievances);

        UserType = (Spinner)findViewById(R.id.userType);
        Department = (Spinner)findViewById(R.id.department);
        Gender = (Spinner)findViewById(R.id.gender);
        IncidenceTime = (Spinner)findViewById(R.id.incidence_time);
        SubmitB = (Button)findViewById(R.id.done);


        String[] UT = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        UserType.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,UT));
        UserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryUT = UserType.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] D = new String[]{"-Select-","","",""};
        Department.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,D));
        Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryD = UserType.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] G = new String[]{"-Select-","","",""};
        Gender.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,G));
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryG = UserType.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] IT = new String[]{"-Select-","","",""};
        IncidenceTime.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,IT));
        IncidenceTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryIT = UserType.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SubmitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OnlineGrievances.this,"Submitted Successfully",Toast.LENGTH_SHORT).show();
                Intent Intent = new Intent(OnlineGrievances.this,HomePage.class);
            }
        });


    }
}