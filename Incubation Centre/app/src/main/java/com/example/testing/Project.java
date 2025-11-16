package com.example.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Collections;

public class Project extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Projects");

    ProjectMyAdapter myAdapter;
    ArrayList<Projectitems> list;
    Button post_btn;
    SharedPreferences prefs;
    boolean isMentor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_project);
        post_btn=findViewById(R.id.projectpost_btn);
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Project.this, Projectpost.class);
                startActivity(intent);
            }
        });
        prefs = getApplicationContext().getSharedPreferences("appData", Context.MODE_PRIVATE);
        isMentor = prefs.getBoolean("isMentor",false);
//        System.out.println(isMentor);

        if(isMentor){
            post_btn.setVisibility(View.GONE);
        }

        recyclerView = findViewById(R.id.posting);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        myAdapter = new ProjectMyAdapter(this, list, isMentor);

        recyclerView.setAdapter(myAdapter);

        getAllPosts();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Projectitems projectitems = dataSnapshot.getValue(Projectitems.class);
                    list.add(projectitems);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAllPosts() {
        Query query=root.orderByChild("Title");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String tittle = ds.child("Title").getValue(String.class);
                    String skill = ds.child("Skill").getValue(String.class);
                    String branches = ds.child("Branch").getValue(String.class);
                    String fields = ds.child("Field").getValue(String.class);
                    String Description = ds.child("Description").getValue(String.class);
                    String email=ds.child("Email").getValue(String.class);
                    String ideasabstract=ds.child("Abstract").getValue(String.class);




                    Projectitems modelPost = new Projectitems(tittle,skill,branches,fields,Description,email,ideasabstract);
                    list.add(modelPost);
                    Collections.reverse(list);
                    myAdapter = new ProjectMyAdapter(Project.this, list, isMentor);
                    recyclerView.setAdapter(myAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(valueEventListener);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void searchproject(final String s){
        ArrayList<Projectitems> copiedList = new ArrayList<>();
        list.forEach(item -> {
            System.out.println(item.getBranch());
            if(item.getBranch() != null){
                System.out.println(item.getBranch().toLowerCase().contains(s.toLowerCase()));
                if(item.getBranch().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }else if(item.getPtitle().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }else if(item.getSkill_req().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getDesc().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getField().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getEmail().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
            }
            Collections.reverse(copiedList);
            myAdapter = new ProjectMyAdapter(Project.this, copiedList, isMentor);
            recyclerView.setAdapter(myAdapter);
        });


    }

    //search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item= menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                getAllPosts();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println(s);
                if (!TextUtils.isEmpty(s.trim())) {
                    searchproject(s);
                } else {
                    getAllPosts();
                }
                return false;
            }

          @Override
          public boolean onQueryTextChange(String s) {
              return true;
          }}
        );


      return  super.onCreateOptionsMenu(menu);

}


}

