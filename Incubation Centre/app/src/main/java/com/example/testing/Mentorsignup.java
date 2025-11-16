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
import android.widget.Spinner;
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


import java.util.HashMap;



public class Mentorsignup extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button signUp;
    EditText  ptitle,mentorEmail,qualifications,designat,skills,fields,desc;
    TextInputEditText user_Password;
    TextView loginBtn,privacy;
    CheckBox checkbox;
    ProgressDialog loadingBar;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference("Profiles").child("Users");
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentorsignup);

        signUp = findViewById(R.id.signup_btn);
        loginBtn = findViewById(R.id.signup_login);
        checkbox =findViewById(R.id.checkbox);
        privacy =findViewById(R.id.privacypolicy);
        ptitle = findViewById(R.id.ptitle);
        mentorEmail = findViewById(R.id.mentor_email);
        qualifications=findViewById(R.id.qualification);
        designat=findViewById(R.id.designation1);
        skills=findViewById(R.id.skills);
        fields=findViewById(R.id.fields);
        desc=findViewById(R.id.desc);
        user_Password = findViewById(R.id.domain);

        loadingBar = new ProgressDialog(Mentorsignup.this);
        mAuth = FirebaseAuth.getInstance();

        privacy.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent signupIntent = new Intent(Mentorsignup.this, Policy.class);
                startActivity(signupIntent);
            }

        });

        loginBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(Mentorsignup.this, Login.class);
                startActivity(loginIntent);
            }

        });


        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ptitle.getText().toString();
                String email = mentorEmail.getText().toString();
                String qualification = qualifications.getText().toString();
                String  designation=designat.getText().toString();
                String skill = skills.getText().toString();
                String field = fields.getText().toString();
                String description = desc.getText().toString();
                String password = user_Password.getText().toString();



                if (TextUtils.isEmpty(email)) {
                    mentorEmail.setError("Email Required");
                }
                if (TextUtils.isEmpty(password)) {
                    user_Password.setError("Password required");
                }
                if (password.length() < 6) {
                    user_Password.setError("Password Must be >=6 characters");
                }

                if (TextUtils.isEmpty(name)) {
                    ptitle.setError("Name required");
                }
                if (TextUtils.isEmpty(qualification)) {
                    qualifications.setError("Qualification  required");
                }
                if (TextUtils.isEmpty(skill)) {
                    skills.setError("designation  required");
                }
                if (TextUtils.isEmpty(designation)) {
                    designat.setError("designation  required");
                }
                if (TextUtils.isEmpty(field)) {
                    fields.setError("fields  required");
                }
                if (!checkbox.isChecked()) {
                    checkbox.setError("Please accept Privacy & Policy");
                } else {
                    loadingBar.setTitle("Creating New Account");
                    loadingBar.setMessage("Please wait,We are creating your Account..");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(true);

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Mentorsignup.this, "User registration Successfull", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DatabaseReference db1 = root.child(userID);
                            HashMap<String, Object> userMap = new HashMap<>();
                            userMap.put("Name", name);
                            userMap.put("Email", email);
                            userMap.put("Qualification", qualification);
                            userMap.put("Skill", skill);
                            userMap.put("Field", field);
                            userMap.put("Desc", description);
                            userMap.put("Designation",designation);
                            //userMap.put("Password", password);
                            userMap.put("Mentor", true);
                            userMap.put("UserId", userID);
                            db1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.exists()) {
                                        db1.updateChildren(userMap);
                                        Toast.makeText(Mentorsignup.this, "add successfully", Toast.LENGTH_SHORT).show();
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


                } }
        });
    }
}