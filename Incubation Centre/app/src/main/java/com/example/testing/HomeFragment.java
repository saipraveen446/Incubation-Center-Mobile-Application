package com.example.testing;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {
    Button getstart;
    FirebaseUser user;
    String userID;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ecell Incubation Center");

        getstart = (Button) view.findViewById(R.id.getstart);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            getstart.setVisibility(View.GONE);
         }
            getstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(user != null){
                        getstart.setVisibility(View.GONE);
                        return;
                    }
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                }
            });

            return view;
        }
}