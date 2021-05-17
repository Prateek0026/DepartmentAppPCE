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

public class AdmissionEnquiry extends AppCompatActivity {

    private Spinner College,CourseName,ExamClear,BoardU,EntranceExam;
    String categoryC,categoryCN,categoryEC,categoryB,categoryEE;
    private Button SubmitB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_enquiry);

        College = (Spinner)findViewById(R.id.college);
        CourseName = (Spinner)findViewById(R.id.course_name);
        ExamClear = (Spinner)findViewById(R.id.examination_cleared);
        BoardU = (Spinner)findViewById(R.id.board);
        EntranceExam = (Spinner)findViewById(R.id.entrance_Exam);
        SubmitB = (Button)findViewById(R.id.done1);


        String[] C = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        College.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,C));
        College.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryC = College.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] CN = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        CourseName.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,CN));
        CourseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryCN = CourseName.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] EC = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        ExamClear.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,EC));
        ExamClear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryEC = ExamClear.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] B = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        BoardU.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,B));
        BoardU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryB = BoardU.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] EE = new String[]{"-Select-","Staff","Student","Other(Stakeholder)"};
        EntranceExam.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,EE));
        EntranceExam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("-Select-"))
                { }
                else{
                    categoryEE = EntranceExam.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SubmitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdmissionEnquiry.this,"Submitted Successfully",Toast.LENGTH_SHORT).show();
                Intent Intent = new Intent(AdmissionEnquiry.this,HomePage.class);
            }
        });

    }
}