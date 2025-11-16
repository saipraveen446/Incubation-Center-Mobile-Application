package com.example.testing;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Posting extends AppCompatActivity {
   FirebaseAuth auth;
   FirebaseUser user;
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Startup");

    MyAdapter myAdapter;
    ArrayList<Details> list;
    Button add_btn;
    SharedPreferences prefs;
    EditText startupdetails;
    Boolean isMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_posting);
        add_btn=findViewById(R.id.ideas_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Posting.this, Posts.class);
                startActivity(intent);
            }
        });

        prefs = getApplicationContext().getSharedPreferences("appData",Context.MODE_PRIVATE);
        isMentor = prefs.getBoolean("isMentor",false);


        if(isMentor){
            add_btn.setVisibility(View.GONE);
        }
        else{
            System.out.println(startupdetails+"");
//            startupdetails.setVisibility(View.GONE);
        }

        recyclerView = findViewById(R.id.posting);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list, isMentor);

        recyclerView.setAdapter(myAdapter);

        getAllPosts();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Details details = dataSnapshot.getValue(Details.class);
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
        Query query=root.orderByChild("Title");
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
                        myAdapter = new MyAdapter(Posting.this,list, isMentor);
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
