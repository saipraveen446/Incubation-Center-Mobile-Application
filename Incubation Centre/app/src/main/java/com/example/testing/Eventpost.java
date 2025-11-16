package com.example.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class Eventpost extends AppCompatActivity {
    EditText event_title, event_desc;
    Button post_btn;
    ListView listView;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userID;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Events");
    String selected_title;
    boolean editing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventpost);
        event_title = findViewById(R.id.event_title);
        event_desc = findViewById(R.id.event_desc);
        post_btn = findViewById(R.id.post_btn);
        mAuth = FirebaseAuth.getInstance();

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = event_title.getText().toString();
                String description = event_desc.getText().toString();
                userID = mAuth.getCurrentUser().getUid();
                String uemail =mAuth.getCurrentUser().getEmail();
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("Title",title);
                userMap.put("Description", description);
                userMap.put("UserID",userID);
                userMap.put("Email",uemail);


                DatabaseReference db1=root.child(title);
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            db1.updateChildren(userMap);
                            Toast.makeText(Eventpost.this, "Added successfully", Toast.LENGTH_SHORT).show();


                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
                Intent intent=new Intent(Eventpost.this,Events.class);
                startActivity(intent);

            }

        });
    }
}
