package com.example.testing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class AccountFragment extends Fragment {
    Button  startuppost, projectpost, eventpost;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root;

    String userID;
    SharedPreferences prefs;
    Button update_p;

    TextView  nameText ,emailText ,skillText ,deptText,qualificationText,designationText ,fieldText ,descText ,interestedareaText;
    TextInputEditText
            idTextview,nameTextview,emailTextview,skillTextview,deptTextview,qualificationTextview,designationTextview,fieldTextview,interestedareaTextview,descTextview;
    public AccountFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Profile");

        startuppost = (Button) view.findViewById(R.id.startuppost);
        projectpost = (Button) view.findViewById(R.id.projectpost);
        eventpost = (Button) view.findViewById(R.id.eventpost);
        update_p =(Button)view.findViewById(R.id.update_p);

        update_p.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();

        prefs = getActivity().getSharedPreferences("appData", Context.MODE_PRIVATE);
        Boolean isMentor = prefs.getBoolean("isMentor", false);
        startuppost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),  Userstartuppost.class);
                startActivity(intent);
            }
        });
        projectpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Userprojectpost.class);
                startActivity(intent);
            }
        });
        eventpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Usereventpost.class);
                startActivity(intent);
            }
        });


        update_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String edited_name = nameTextview.getText().toString();
              String  edited_skill =skillTextview.getText().toString();
              String  edited_dept =deptTextview.getText().toString();
              String  edited_qualification=qualificationTextview.getText().toString();
              String  edited_designation=designationTextview.getText().toString();
              String  edited_field=fieldTextview.getText().toString();
              String  edited_desc=descTextview.getText().toString();
              String  edited_interestedarea=interestedareaTextview.getText().toString();

              Map profileupdate = new HashMap();
              profileupdate.put("Name", edited_name);
              profileupdate.put("Skill",edited_skill);
              profileupdate.put("Department",edited_dept);
              profileupdate.put("Qualification",edited_qualification);
              profileupdate.put("Designation",edited_designation);
              profileupdate.put("Field",edited_field);
              profileupdate.put("Desc",edited_desc);
              profileupdate.put("Interetarea",edited_interestedarea);
              db.getReference("Profiles").child("Users").child(mAuth.getCurrentUser().getUid())
                      .updateChildren(profileupdate).addOnCompleteListener(new OnCompleteListener() {
                  @Override
                  public void onComplete(@NonNull Task task) {
                      System.out.println("Completed");
                      System.out.println(task.getResult());
                      nameTextview.setEnabled(false);
                      skillTextview.setEnabled(false);
                      deptTextview.setEnabled(false);
                      qualificationTextview.setEnabled(false);
                      designationTextview.setEnabled(false);
                      fieldTextview.setEnabled(false);
                      descTextview.setEnabled(false);
                      interestedareaTextview.setEnabled(false);
                  }
              });
            }
        });

         nameText = (TextView) view.findViewById(R.id.nameText);
         emailText = (TextView) view.findViewById(R.id.emailText);
         skillText = (TextView) view.findViewById(R.id.skillText);
         deptText = (TextView)view.findViewById(R.id.deptText);
         qualificationText = (TextView) view.findViewById(R.id.qualificationText);
         designationText=(TextView) view.findViewById(R.id.designationText);
         fieldText = (TextView) view.findViewById(R.id.fieldText);
         descText = (TextView) view.findViewById(R.id.descText);
         interestedareaText=(TextView)view.findViewById(R.id.interestedareasText);

         TextView idTextview =  view.findViewById(R.id.id);
         nameTextview = view.findViewById(R.id.name_p);
         emailTextview = view.findViewById(R.id.email_p);
         skillTextview = view.findViewById(R.id.skill_p);
         deptTextview =view.findViewById(R.id.dept_p);
         qualificationTextview =  view.findViewById(R.id.qualification_p);
         designationTextview=view.findViewById(R.id.designation_p);
         fieldTextview = view.findViewById(R.id.field_p);
          interestedareaTextview=view.findViewById(R.id.interestedareas_p);
         TextView userpost=view.findViewById(R.id.userposts);
         descTextview =  view.findViewById(R.id.desc_p);
        if (isMentor) {
            nameTextview.setText(prefs.getString("name", "No Name"));
            emailTextview.setText(prefs.getString("email", "example@google.com"));
            skillTextview.setText(prefs.getString("skill", "No skill"));
            qualificationTextview.setText(prefs.getString("qualification", "Null"));
            designationTextview.setText(prefs.getString("designation","desig"));
            fieldTextview.setText(prefs.getString("field", "No interest areas"));
            descTextview.setText(prefs.getString("desc", "No desc"));


            startuppost.setVisibility(view.GONE);
            projectpost.setVisibility(view.GONE);
           // eventpost.setVisibility(view.GONE);
           // userpost.setVisibility(view.GONE);
            deptTextview.setVisibility(view.GONE);
            deptText.setVisibility(view.GONE);
            interestedareaText.setVisibility(view.GONE);
            interestedareaTextview.setVisibility(view.GONE);


        } else {
            nameTextview.setText(prefs.getString("name", "No Name"));
            emailTextview.setText(prefs.getString("email", "example@google.com"));
            skillTextview.setText(prefs.getString("skill", "No skill"));
            deptTextview.setText(prefs.getString("department","NO Dept"));
            //System.out.println(prefs.getAll().toString());
            interestedareaTextview.setText(prefs.getString("interestedareas", "No interest areas"));
            idTextview.setText(prefs.getString("idnum", "No Id"));
            qualificationText.setVisibility(view.GONE);
            qualificationTextview.setVisibility(view.GONE);
            fieldText.setVisibility(view.GONE);
            fieldTextview.setVisibility(view.GONE);
            descText.setVisibility(view.GONE);
            descTextview.setVisibility(view.GONE);
            designationText.setVisibility(view.GONE);
            designationTextview.setVisibility(view.GONE);


        }


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu,  MenuInflater inflater) {
        inflater.inflate(R.menu.account,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit:
                nameTextview.setEnabled(true);
                skillTextview.setEnabled(true);
                qualificationTextview.setEnabled(true);
                fieldTextview.setEnabled(true);
                descTextview.setEnabled(true);
                designationTextview.setEnabled(true);
                interestedareaTextview.setEnabled(true);
                update_p.setVisibility(View.VISIBLE);


                return true;
            case R.id.settings:
                Toast.makeText(getActivity(), "settings selected", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(),Settings.class);
                    startActivity(i);
                return true;


            case R.id.logout:
                user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Logout successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}






