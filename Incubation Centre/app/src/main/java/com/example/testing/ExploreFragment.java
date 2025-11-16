package com.example.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ExploreFragment extends Fragment {
    FirebaseUser user;
    CardView About, startupbtn, project, research, eventbtn;
    String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dashboard");


        About = view.findViewById(R.id.About_btn);
        startupbtn = view.findViewById(R.id.startup_btn);
        project = view.findViewById(R.id.project_button);
        research = view.findViewById(R.id.research_area_btn);
        eventbtn = view.findViewById(R.id.event_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        if (user != null) {
            About.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), About.class);
                    startActivity(intent);

                }
            });
            startupbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Posting.class);
                    startActivity(intent);

                }
            });
            project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Project.class);
                    startActivity(intent);

                }
            });
            research.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Mentor.class);
                    startActivity(intent);

                }
            });

            eventbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Events.class);
                    startActivity(intent);

                }
            });
        }

        return view;
    }
}