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

public class Userstartuppost extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user;
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Startup");

    UserstartuppostMyAdapter myAdapter;
    ArrayList<Details> list;
   // Button edit_btn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_userstartuppost);
       // edit_btn=findViewById(R.id.edit_btn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
//        edit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Userstartuppost.this, Posts.class);
//                startActivity(intent);
//            }
//        });


        recyclerView = findViewById(R.id.startupposting);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new UserstartuppostMyAdapter(this, list);

        recyclerView.setAdapter(myAdapter);

        getAllPosts();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Details details = dataSnapshot.getValue(Details.class);
                    // Log.d("Details tag",details.ptitle);
                    list.add(details);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAllPosts() { ;
        Query query=root.orderByChild("UserID").equalTo(userID);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String tittle = ds.child("Title").getValue(String.class);
                    String Skill = ds.child("Skill").getValue(String.class);
                    String Description = ds.child("Description").getValue(String.class);
                    String Email=ds.child("Email").getValue(String.class);
                    String Startupdetails= ds.child("Abstract").getValue(String.class);

                    Details modelPost = new Details(tittle,Skill,Description,Email,Startupdetails);
                    list.add(modelPost);
                    Collections.reverse(list);
                    myAdapter = new UserstartuppostMyAdapter(Userstartuppost.this,list);
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
