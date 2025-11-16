package com.example.testing;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MentorMyAdapter extends RecyclerView.Adapter<MentorMyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Mentoritems> list;

    public MentorMyAdapter(Context context, ArrayList<Mentoritems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mentordetails, parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mentoritems mentoritems = list.get(position);
       holder.ptitle.setText(mentoritems.getPtitle());
        holder.mentorEmail.setText(mentoritems.getMentorEmail());
        holder.qualifications.setText(mentoritems.getQualifications());
        holder.designat.setText(mentoritems.getDesignat());
        holder.skills.setText(mentoritems.getSkills());
       holder.fields.setText(mentoritems.getFields());
       holder.desc.setText(mentoritems.getDesc());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ptitle,mentorEmail,qualifications,designat,skills,fields,desc;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ptitle =itemView.findViewById(R.id.mentorname);
            mentorEmail=itemView.findViewById(R.id.mentoremail);
            qualifications=itemView.findViewById(R.id.mentorqualification);
            designat=itemView.findViewById(R.id.mentordesignation);
            skills=itemView.findViewById(R.id.skills);
            fields=itemView.findViewById(R.id.mentorfield);
            desc=itemView.findViewById(R.id.mentordesc);


        }
    }
}




