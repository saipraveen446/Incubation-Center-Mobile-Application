package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Signup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText userEmail,userPassword;
    Button loginBtn;
    TextView signUpBtn,forgotpassword;
    FirebaseAuth mAuth;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intializations();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotpasswordActivity();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpActivity();
            }
        });

    }




    private void intializations() {
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.domain);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.login_signup);
        forgotpassword=findViewById(R.id.forgotpasswd);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
    }

    private void signUpActivity() {
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }

    private void mentorsignUpActivity() {
        Intent intent = new Intent(Login.this, Mentorsignup.class);
        startActivity(intent);
    }
    private void forgotpasswordActivity() {
        Intent intent = new Intent(Login.this, Forgotpassword.class);
        startActivity(intent);
    }

    private void loginUser() {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            userEmail.setError("Email cannont be empty");
            userEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            userPassword.setError("Password cannont be empty");
            userPassword.requestFocus();
        }else{
            loadingBar.setTitle("Login..");
            loadingBar.setMessage("Please wait ..");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        loadingBar.dismiss();
                    }
                    else {
                        Toast.makeText(Login.this,"Login Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }

    }
}