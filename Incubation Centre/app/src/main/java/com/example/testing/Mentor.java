package com.example.testing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Mentor extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference("Profiles").child("Users");

    MentorMyAdapter myAdapter;
    ArrayList<Mentoritems> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_mentor);
        recyclerView = findViewById(R.id.Mentors);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MentorMyAdapter(this, list);

        recyclerView.setAdapter(myAdapter);

        getAllPosts();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Mentoritems mentoritems = dataSnapshot.getValue(Mentoritems.class);
                    list.add(mentoritems);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAllPosts() {
        Query query=root.orderByChild("Mentor").equalTo(true);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String name = ds.child("Name").getValue(String.class);
                    String email = ds.child("Email").getValue(String.class);
                    String qualification = ds.child("Qualification").getValue(String.class);
                    String  designation=ds.child("Designation").getValue(String.class);
                    String skill = ds.child("Skill").getValue(String.class);
                    String field = ds.child("Field").getValue(String.class);
                    String description = ds.child("Desc").getValue(String.class);



                    Mentoritems modelPost = new Mentoritems(name,email,qualification,designation,skill,field,description);
                    list.add(modelPost);
                    Collections.reverse(list);
                    myAdapter = new MentorMyAdapter(Mentor.this, list);
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
        ArrayList<Mentoritems> copiedList = new ArrayList<>();
//        list.clear();
        list.forEach(item -> {
            System.out.println(item.getMentorEmail());
            if(item.getMentorEmail() != null){
                System.out.println(item.getMentorEmail().toLowerCase().contains(s.toLowerCase()));
                if(item.getPtitle().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }else if(item.getSkills().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }else if(item.getDesignat().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getDesc().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getFields().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
                else if(item.getQualifications().toLowerCase().contains(s.toLowerCase())){
                    copiedList.add(item);
                }
            }
            Collections.reverse(copiedList);
            myAdapter = new MentorMyAdapter(Mentor.this, copiedList);
            recyclerView.setAdapter(myAdapter);
        });


    }

   // search
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
