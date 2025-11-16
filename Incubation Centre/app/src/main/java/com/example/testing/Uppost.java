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
import android.widget.TextView;
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

import java.util.HashMap;

public class Uppost extends AppCompatActivity{
    FirebaseAuth mAuth;
    FirebaseUser user;
    EditText ptitle, skill_req,branch,field,desc,ideaabstract;
    Button post_btn;
    ListView listView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Projects");
    String userID;
    String selected_title;
    boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectpost);
        Bundle extras = getIntent().getExtras();

        selected_title = extras.getString("title");
        String selected_skill=extras.getString("skill");
        String selected_branch=extras.getString("branches");
        String selected_field=extras.getString("fields");
        String selected_desc = extras.getString("description");
        String selected_abstract=extras.getString("ideasabstract");


        ptitle = findViewById(R.id.title_req);
        skill_req = findViewById(R.id.skill_req);
        branch = findViewById(R.id.branch_req);
        field = findViewById(R.id.field_req);
        desc=findViewById(R.id.desc);
        ideaabstract=findViewById(R.id.ideaabstract);
        post_btn = findViewById(R.id.post_btn);

        mAuth = FirebaseAuth.getInstance();
        if(selected_title != null){
            ptitle.setText(selected_title.toString());
            skill_req.setText(selected_skill.toString());
            branch.setText(selected_branch.toString());
            field.setText(selected_field.toString());
            desc.setText(selected_desc.toString());
            ideaabstract.setText(selected_abstract.toString());
        }
        if(selected_title != null && selected_desc != null){
            editing = true;
        }

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editing == true) {
                    db.getReference("Projects").child(selected_title).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            System.out.println("Deleted....");
                        }
                    });

                    String title = ptitle.getText().toString();
                    String skill = skill_req.getText().toString();
                    String branches = branch.getText().toString();
                    String fields = field.getText().toString();
                    String description = desc.getText().toString();
                    String ideasabstract = ideaabstract.getText().toString();
                    userID = mAuth.getCurrentUser().getUid();
                    String uemail = mAuth.getCurrentUser().getEmail();
                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("Title", title);
                    userMap.put("Skill", skill);
                    userMap.put("Branch", branches);
                    userMap.put("Field", fields);
                    userMap.put("Description", description);
                    userMap.put("Email", uemail);
                    userMap.put("Abstract", ideasabstract);
                    userMap.put("UserID", userID);


                    DatabaseReference db1 = root.child(title);
                    db1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                db1.updateChildren(userMap);
                                Toast.makeText(Uppost.this, "Added successfully", Toast.LENGTH_SHORT).show();


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Intent intent = new Intent(Uppost.this, Project.class);
                    startActivity(intent);

                }
            }});
    }
}
