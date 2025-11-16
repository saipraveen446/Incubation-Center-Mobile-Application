package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.HashMap;

public class Uspost extends AppCompatActivity {
    EditText ptitle, skill_req,desc,startup_details;

    Button post_btn;
    ListView listView;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userID;
    String selected_title;
    boolean editing;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Startup");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Bundle extras = getIntent().getExtras();
        selected_title = extras.getString("title");
        String selected_skill=extras.getString("skill");
        String selected_desc = extras.getString("description");
        String selected_Abstract=extras.getString("startupdetails");

        ptitle = findViewById(R.id.ptitle);
        skill_req = findViewById(R.id.skill_req);
        desc=findViewById(R.id.desc);
        startup_details = findViewById(R.id.startupdetails);
        post_btn = findViewById(R.id.post_btn);
        mAuth = FirebaseAuth.getInstance();

        if(selected_title != null){
            ptitle.setText(selected_title.toString());
            skill_req.setText(selected_skill.toString());
            desc.setText(selected_desc.toString());
            startup_details.setText(selected_Abstract.toString());
        }
        if(selected_title != null && selected_desc != null){
            editing = true;
        }


        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editing == true) {
                    db.getReference("Startup").child(selected_title).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            System.out.println("Deleted....");
                        }
                    });


                    String title = ptitle.getText().toString();
                    String skill = skill_req.getText().toString();
                    String description = desc.getText().toString();
                    String startupdetails = startup_details.getText().toString();
                    userID = mAuth.getCurrentUser().getUid();
                    String uemail = mAuth.getCurrentUser().getEmail();
                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("Title", title);
                    userMap.put("Skill", skill);
                    userMap.put("Description", description);
                    userMap.put("Email", uemail);
                    userMap.put("Abstract", startupdetails);
                    userMap.put("UserID", userID);


                    DatabaseReference db1 = root.child(title);
                    db1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                db1.updateChildren(userMap);
                                Toast.makeText(Uspost.this, "Added successfully", Toast.LENGTH_SHORT).show();


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Intent intent = new Intent(Uspost.this, Posting.class);
                    startActivity(intent);


                }
            } });
    }
}

