package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.testing.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
   FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseReference usersRef = db.getReference("Profiles").child("Users");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user != null) {
            usersRef.orderByChild("Email").equalTo(user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    SharedPreferences prefs = getSharedPreferences("appData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    for (DataSnapshot dp : task.getResult().getChildren()) {
                        Boolean isMentor = (Boolean) dp.child("Mentor").getValue();
                        String name = (String) dp.child("Name").getValue();
                        String email = (String) dp.child("Email").getValue();
                        String idnum = (String) dp.child("IdNUm").getValue();
                        String skill = (String) dp.child("Skill").getValue();
                        String department=(String)dp.child("Department").getValue();
                        String  qualification=(String)dp.child("Qualification").getValue();
                        String  designation=(String)dp.child("Designation").getValue();
                        String field=(String)dp.child("Field").getValue();
                        String interestedareas=(String)dp.child("Interestareas").getValue();
                        String desc=(String)dp.child("Desc").getValue();

                        if(isMentor){

                            System.out.println("Mentorr");
                        editor.putBoolean("isMentor", true);
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putString("qualification",qualification);
                            editor.putString("designation",designation);
                            editor.putString("field",field);
                            editor.putString("desc",desc);
                            editor.putString("skill",skill);


                        }
                    else {
                        System.out.println(interestedareas);
                        editor.putBoolean("isMentor", false);
                            editor.putString("idnum",idnum);
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putString("skill",skill);
                            editor.putString("department",department);
                            editor.putString("interestedareas",interestedareas);
                        }
                    }
                    editor.apply();
                }
            });
        }
        replaceFragment(new HomeFragment());
        binding.bottomNav.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){
                case R.id.home_nav:

                    replaceFragment(new HomeFragment());
                    break;
                case R.id.explore_nav:
                    if(FirebaseAuth.getInstance().getCurrentUser()== null){
                        Intent intent = new Intent(this,Login.class);
                        startActivity(intent);
                        break;
                    }else {
                        replaceFragment(new ExploreFragment());
                        break;
                    }

                case R.id.notification_nav:
                    if(FirebaseAuth.getInstance().getCurrentUser()== null){
                        Intent intent = new Intent(this,Login.class);
                        startActivity(intent);
                        break;
                    }else {
                        replaceFragment(new UserFragment());
                        break;
                    }
                case R.id.person_nav:
                    if(FirebaseAuth.getInstance().getCurrentUser()== null){
                        Intent intent = new Intent(this,Login.class);
                        startActivity(intent);
                        break;
                    }else {
                        replaceFragment(new AccountFragment());
                        break;
                    }
            }

            return true;
        } );

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}