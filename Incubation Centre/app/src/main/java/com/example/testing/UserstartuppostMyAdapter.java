package com.example.testing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class UserstartuppostMyAdapter extends RecyclerView.Adapter<UserstartuppostMyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Details> list;
    boolean isMentor;

    public UserstartuppostMyAdapter(Context context, ArrayList<Details> list, Boolean isMentor) {
        this.context = context;
        this.list = list;
        this.isMentor = isMentor;
    }

    public UserstartuppostMyAdapter(Context context, ArrayList<Details> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ideasdetails, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Details details = list.get(position);
        holder.ptitle.setText(details.getPtitle());
        holder.skill_req.setText(details.getSkill_req());
        holder.desc.setText(details.getDesc());
        holder.email.setText(details.getEmail());
        holder.startupdetails.setText(details.getStartupdetails());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,Uspost.class);
                i.putExtra("title",details.getPtitle());
                i.putExtra("skill",details.getSkill_req());
                i.putExtra("description",details.getDesc());
                i.putExtra("uemail",details.getEmail());
                i.putExtra("startupdetails",details.getStartupdetails());
                context.startActivity(i);
                holder.ptitle.setText(details.getPtitle());
                holder.skill_req.setText(details.getSkill_req());
                holder.desc.setText(details.getDesc());
                holder.email.setText(details.getEmail());
                holder.startupdetails.setText(details.getStartupdetails());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ptitle, skill_req,desc,email;
        MaterialTextView startupdetails;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ptitle =itemView.findViewById(R.id.ideaptitle);
            skill_req =itemView.findViewById(R.id.ideaskills);
            desc=itemView.findViewById(R.id.ideadesc);
            email = itemView.findViewById(R.id.ideaemail);
            startupdetails=itemView.findViewById(R.id.ideaabstract);

        }
    }
}

