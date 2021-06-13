package com.example.collegeapp.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeapp.AboutUs;
import com.example.collegeapp.Admin;
import com.example.collegeapp.AdmissionEnquiry;
import com.example.collegeapp.Chairman;
import com.example.collegeapp.DepartmentsPage;
import com.example.collegeapp.HOD;
import com.example.collegeapp.HomePage;
import com.example.collegeapp.Leader;
import com.example.collegeapp.OnlineGrievances;
import com.example.collegeapp.R;
import com.example.collegeapp.ReadAtGlance;
import com.example.collegeapp.SliderAdp;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
    private SliderView sliderView;
    private int[] images = {R.drawable.c1,R.drawable.c2,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,
            R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13,R.drawable.c4,R.drawable.c5};

    SliderAdp sliderAdp;

    private TextView CM,LE,AD,V,O,M;

    private ImageView GF, AE,OFP,DP,AU;
    private Button RM;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = (SliderView)view.findViewById(R.id.slider_view);
        GF = (ImageView) view.findViewById(R.id.B1);
        OFP = (ImageView)view.findViewById(R.id.B2);
        AE = (ImageView)view.findViewById(R.id.B3);
        DP = (ImageView)view.findViewById(R.id.B4);
        AU = (ImageView)view.findViewById(R.id.B5);
        RM = (Button) view.findViewById(R.id.read);
        CM = (TextView)view.findViewById(R.id.hod);
        CM.setMovementMethod(LinkMovementMethod.getInstance());
        AD = (TextView)view.findViewById(R.id.admin);
        V = (TextView)view.findViewById(R.id.vision);
        M = (TextView)view.findViewById(R.id.mission);
        O = (TextView)view.findViewById(R.id.objectives);

        sliderAdp = new SliderAdp(images);
        sliderView.setSliderAdapter(sliderAdp);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.startAutoCycle();

        GF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OnlineGrievances.class);
                startActivity(intent);
            }
        });

        AE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdmissionEnquiry.class);
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
                Intent intent = new Intent(getActivity(), DepartmentsPage.class);
                startActivity(intent);
            }
        });

        AU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUs.class);
                startActivity(intent);
            }
        });

        RM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadAtGlance.class);
                startActivity(intent);
            }
        });

        CM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HOD.class);
                startActivity(intent);
            }
        });


        AD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Admin.class);
                startActivity(intent);
            }
        });

        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadAtGlance.class);
                startActivity(intent);
            }
        });

        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadAtGlance.class);
                startActivity(intent);
            }
        });

        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadAtGlance.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}