package com.example.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class UserMyAdapter extends RecyclerView.Adapter<UserMyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Useritems> list;

    public UserMyAdapter(Context context, ArrayList<Useritems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userdetails, parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Useritems useritems=list.get(position);
        holder.id.setText(useritems.getIdNUm());
        holder.username.setText(useritems.getName());
        holder.userEmail.setText(useritems.getEmail());
        holder.skills.setText(useritems.getSkill());
        holder.dept.setText(useritems.getDept());
        holder.interestedarea.setText(useritems.getInterestedarea());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, username, userEmail, skills,dept,interestedarea;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id =itemView.findViewById(R.id.userid);
            username=itemView.findViewById(R.id.username);
            userEmail=itemView.findViewById(R.id.useremail);
            skills=itemView.findViewById(R.id.userskill);
            dept=itemView.findViewById(R.id.userdept);
            interestedarea=itemView.findViewById(R.id.interestedareas);


        }
    }
}

