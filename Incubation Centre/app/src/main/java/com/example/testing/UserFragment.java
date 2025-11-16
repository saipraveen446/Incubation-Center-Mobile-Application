package com.example.testing;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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

public class UserFragment extends Fragment {
    FirebaseUser user;
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference("Profiles").child("Users");

    UserMyAdapter myAdapter;
    ArrayList<Useritems> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Users");

        user = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = view.findViewById(R.id.userview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        myAdapter = new UserMyAdapter(getActivity(), list);
        recyclerView.setAdapter(myAdapter);
        getAllPosts();
        return view;
    }


    private void getAllPosts() {
        Query query = root.orderByChild("Mentor").equalTo(false);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String idnum = (String) ds.child("IdNUm").getValue();
                    String name = (String) ds.child("Name").getValue();
                    String email = (String) ds.child("Email").getValue();
                    String skill = (String) ds.child("Skill").getValue();
                    String department = (String) ds.child("Department").getValue();
                    String interestedarea = (String) ds.child("Interestareas").getValue();

                    Useritems modelPost = new Useritems(email, name, skill, department, interestedarea, idnum);
                    list.add(modelPost);
                    Collections.reverse(list);
                    myAdapter = new UserMyAdapter(getActivity(), list);
                    recyclerView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addValueEventListener(valueEventListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void searchproject(final String s) {
        ArrayList<Useritems> copiedList = new ArrayList<>();
//        list.clear();
        list.forEach(item -> {
            System.out.println(item.getIdNUm());
            if (item.getIdNUm() != null) {
                System.out.println(item.getIdNUm().toLowerCase().contains(s.toLowerCase()));
                if (item.getName().toLowerCase().contains(s.toLowerCase())) {
                    copiedList.add(item);
                } else if (item.getEmail().toLowerCase().contains(s.toLowerCase())) {
                    copiedList.add(item);
                } else if (item.getSkill().toLowerCase().contains(s.toLowerCase())) {
                    copiedList.add(item);
                } else if (item.getInterestedarea().toLowerCase().contains(s.toLowerCase())) {
                    copiedList.add(item);
                } else if (item.getDept().toLowerCase().contains(s.toLowerCase())) {
                    copiedList.add(item);
                }
            }
            Collections.reverse(copiedList);
            myAdapter = new UserMyAdapter(getActivity(), copiedList);
            recyclerView.setAdapter(myAdapter);
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
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
                                              }
                                          }
        );





    }
}
