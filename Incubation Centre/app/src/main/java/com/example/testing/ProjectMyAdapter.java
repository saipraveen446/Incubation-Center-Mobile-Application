package com.example.testing;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectMyAdapter extends RecyclerView.Adapter<ProjectMyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Projectitems> list;
    boolean isMentor;

    public ProjectMyAdapter(Context context, ArrayList<Projectitems> list) {
        this.context = context;
        this.list = list;
    }
    public ProjectMyAdapter(Context context, ArrayList<Projectitems> list, Boolean isMentor) {
        this.context = context;
        this.list = list;
        this.isMentor = isMentor;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.projectdetails, parent, false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Projectitems projectitems = list.get(position);
        holder.ptitle.setText(projectitems.getPtitle());
        holder.skill_req.setText(projectitems.getSkill_req());
        holder.branch.setText(projectitems.getBranch());
        holder.field.setText(projectitems.getField());
        holder.desc.setText(projectitems.getDesc());
        holder.email.setText(projectitems.getEmail());
        if(this.isMentor) {
            holder.ideaabstract.setText(projectitems.getIdeaabstract());
        }else {
            holder.ideaabstract.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ptitle, skill_req,branch,field,desc,email,ideaabstract;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ptitle =itemView.findViewById(R.id.ideaptitle);
            skill_req =itemView.findViewById(R.id.ideaskills);
            branch=itemView.findViewById(R.id.ideasbranch);
            field=itemView.findViewById(R.id.ideasfield);
            desc=itemView.findViewById(R.id.ideadesc);
            email=itemView.findViewById(R.id.ideaemail);
            ideaabstract=itemView.findViewById(R.id.ideaabstract);


        }
    }
}



