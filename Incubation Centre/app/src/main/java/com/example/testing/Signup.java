package com.example.testing;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button signUp;
    CheckBox checkbox;
    EditText userEmail, id, username,skills,dept,interestedarea;
    TextInputEditText user_Password;
    TextView loginBtn,mentorsignup,privacy;
    ProgressDialog loadingBar;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference("Profiles").child("Users");

    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = findViewById(R.id.signup_btn);
        loginBtn = findViewById(R.id.signup_login);
        checkbox=findViewById(R.id.checkbox);
        privacy=findViewById(R.id.privacypolicy);
        mentorsignup = findViewById(R.id.mentor_signup);
        id = findViewById(R.id.id);
        skills=findViewById(R.id.skills);
        dept=findViewById(R.id.dept);
        interestedarea = findViewById(R.id.interestedareas);
        username = findViewById(R.id.ptitle);
        userEmail = findViewById(R.id.signup_email);
        user_Password = findViewById(R.id.domain);


        loadingBar = new ProgressDialog(Signup.this);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(Signup.this, Login.class);
                startActivity(loginIntent);
            }

        });

        mentorsignup.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent signupIntent = new Intent(Signup.this, Mentorsignup.class);
                startActivity(signupIntent);
            }

        });
        privacy.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent signupIntent = new Intent(Signup.this, Policy.class);
                startActivity(signupIntent);
            }

        });



            signUp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = username.getText().toString();
                    String idnum = id.getText().toString();
                    String email = userEmail.getText().toString();
                    String department=dept.getText().toString();
                    String skill = skills.getText().toString();
                    String interestedareas = interestedarea.getText().toString();
                    String password = user_Password.getText().toString();

                    if (TextUtils.isEmpty(email)) {
                        userEmail.setError("Email Required");
                    }
                    if (TextUtils.isEmpty(password)) {
                        user_Password.setError("Password required");
                    }
                    if (password.length() < 6) {
                        user_Password.setError("Password Must be >=6 characters");
                    }
                    if (TextUtils.isEmpty(idnum)) {
                        id.setError("id required");
                    }
                    if (TextUtils.isEmpty(skill)) {
                        dept.setError("skill required");
                    }
                    if (TextUtils.isEmpty(skill)) {
                        skills.setError("skill required");
                    }
                    if (TextUtils.isEmpty(name)) {
                        username.setError("Name required");
                    }
                    if (!checkbox.isChecked()) {
                        checkbox.setError("Please accept Privacy & Policy");
                    }else {
                        loadingBar.setTitle("Creating New Account");
                        loadingBar.setMessage("Please wait,We are creating your Account..");
                        loadingBar.show();
                        loadingBar.setCanceledOnTouchOutside(true);

                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task -> {
                            if (task.isSuccessful()) {
                                userID = mAuth.getCurrentUser().getUid();
                                DatabaseReference db1 = root.child(userID);
                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("Name", name);
                                userMap.put("IdNUm", idnum);
                                userMap.put("Email", email);
                                userMap.put("Skill", skill);
                                userMap.put("Department",department);
                                userMap.put("Interestareas", interestedareas);
                               // userMap.put("Password", password);
                                userMap.put("UserId", userID);
                                userMap.put("Mentor", false);

                                db1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (!snapshot.exists()) {
                                            db1.updateChildren(userMap);
                                            Toast.makeText(Signup.this, "Register successfully", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                                startActivity(new Intent(getApplicationContext(), Login.class));
                            } else {


                            }

                        }));


                    }
                }});



}}
