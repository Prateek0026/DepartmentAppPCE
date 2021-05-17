package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomePage extends AppCompatActivity {

    private SliderView sliderView;
    private int[] images = {R.drawable.c1,R.drawable.c2,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,
                R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13,R.drawable.c4,R.drawable.c5};

    SliderAdp sliderAdp;

    private TextView CM,LE,AD,V,O,M;

    private ImageView GF, AE,OFP,DP,AU;
    private Button RM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sliderView = (SliderView)findViewById(R.id.slider_view);
        GF = (ImageView) findViewById(R.id.B1);
        OFP = (ImageView)findViewById(R.id.B2);
        AE = (ImageView)findViewById(R.id.B3);
        DP = (ImageView)findViewById(R.id.B4);
        AU = (ImageView)findViewById(R.id.B5);
        RM = (Button) findViewById(R.id.read);
        CM = (TextView)findViewById(R.id.chairMan);
        CM.setMovementMethod(LinkMovementMethod.getInstance());
        LE = (TextView)findViewById(R.id.leader);
        AD = (TextView)findViewById(R.id.admin);
        V = (TextView)findViewById(R.id.vision);
        M = (TextView)findViewById(R.id.mission);
        O = (TextView)findViewById(R.id.objectives);

        sliderAdp = new SliderAdp(images);
        sliderView.setSliderAdapter(sliderAdp);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.startAutoCycle();

        GF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,OnlineGrievances.class);
                startActivity(intent);
            }
        });

        AE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,AdmissionEnquiry.class);
                startActivity(intent);
            }
        });

        OFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("http://erp.ltjss.net/");
            }
        });

        DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,DepartmentsPage.class);
                startActivity(intent);
            }
        });

        AU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,AboutUs.class);
                startActivity(intent);
            }
        });

        RM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ReadAtGlance.class);
                startActivity(intent);
            }
        });

        CM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,Chairman.class);
                startActivity(intent);
            }
        });

        LE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,Leader.class);
                startActivity(intent);
            }
        });

        AD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,Admin.class);
                startActivity(intent);
            }
        });

        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ReadAtGlance.class);
                startActivity(intent);
            }
        });

        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ReadAtGlance.class);
                startActivity(intent);
            }
        });

        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ReadAtGlance.class);
                startActivity(intent);
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}