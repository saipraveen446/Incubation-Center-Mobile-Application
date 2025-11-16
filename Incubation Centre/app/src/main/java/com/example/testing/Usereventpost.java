package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Usereventpost extends AppCompatActivity {
    Button edit;
    RecyclerView recyclerView;
    FirebaseUser user;
    FirebaseAuth auth;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Events");
    UsereventpostMyAdapter myAdapter;
    ArrayList<Eventitems> list;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_usereventpost);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        recyclerView = findViewById(R.id.Eventspost);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new UsereventpostMyAdapter(Usereventpost.this, list);
        recyclerView.setAdapter(myAdapter);

        geAllPosts();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Eventitems eventitems = dataSnapshot.getValue(Eventitems.class);
                    list.add(eventitems);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void geAllPosts() {
        Query query=root.orderByChild("UserID").equalTo(userID);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String tittle = ds.child("Title").getValue(String.class);
                    String email =ds.child("Email").getValue(String.class);
                    String Description = ds.child("Description").getValue(String.class);


                    Eventitems modelPost = new Eventitems(tittle,email,Description);
                    list.add(modelPost);
                    Collections.reverse(list);
                    myAdapter = new UsereventpostMyAdapter(Usereventpost.this,list);
                    recyclerView.setAdapter(myAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(valueEventListener);


    }


}