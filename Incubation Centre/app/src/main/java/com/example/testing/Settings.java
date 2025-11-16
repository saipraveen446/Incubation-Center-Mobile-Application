package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    TextView changePassword, help,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changePassword = findViewById(R.id.change);
        help = findViewById(R.id.contact);
        feedback=findViewById(R.id.feedback);



        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(Settings.this, Forgotpassword.class);
                startActivity(loginIntent);
            }

        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendmail();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmail();
            }
        });



    }

    private void sendmail() {
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.setType("text/plain");
        sendEmail.putExtra(sendEmail.EXTRA_EMAIL, new String[]{"n160446@rguktn.ac.in"});
        try {
            startActivity(Intent.createChooser(sendEmail, "Choose an Email Client"));
        } catch (Exception e) {
            Toast.makeText(Settings.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}