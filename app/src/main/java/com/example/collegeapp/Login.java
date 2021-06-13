package com.example.collegeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class Login extends AppCompatActivity {

    Button login;
    EditText erp , pass;
    public TextView welcome;
    private ProgressBar progressBar;
    ImageView UserPic;
    private  final int REQ = 1;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        welcome = (TextView)findViewById(R.id.To);
        login = (Button)findViewById(R.id.login);
        erp = (EditText) findViewById(R.id.erp);
        pass = (EditText)findViewById(R.id.password);
        UserPic = (ImageView)findViewById(R.id.userPic);

        welcome.setText(R.string.HeadingLogin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.To);

        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RelativeLayout layout = findViewById(R.id.display);

        UserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = new ProgressBar(Login.this, null, android.R.attr.progressBarStyleLarge);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                layout.addView(progressBar, params);
                progressBar.setVisibility(View.VISIBLE);
                CheckLogin(erp.getText().toString(),pass.getText().toString());
                if(erp.getText().toString().equals("1800") && pass.getText().toString().equals("1234") )
                {
                    Intent intent = new Intent(Login.this,NavigationPage.class);
                    startActivity(intent);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"Wrong Details",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    private void OpenGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        welcome.setText(R.string.HeadingLogin);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK)
        {
            Uri url = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserPic.setImageBitmap(bitmap);
        }
    }


    public void CheckLogin(String Erp, String Pass)
    {
        if(Erp.isEmpty())
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Login.this,"Please Enter ERP ID",Toast.LENGTH_SHORT).show();
        }
        else if(Pass.isEmpty())
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
        }
    }

}