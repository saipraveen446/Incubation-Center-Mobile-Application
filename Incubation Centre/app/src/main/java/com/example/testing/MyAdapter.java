package com.example.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Details> list;
    boolean isMentor;

    public MyAdapter(Context context, ArrayList<Details> list, Boolean isMentor) {
        this.context = context;
        this.list = list;
        this.isMentor = isMentor;
    }

    public MyAdapter(Context context, ArrayList<Details> list) {
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
        System.out.println(isMentor+" Mentor");
        if(this.isMentor) {
            holder.startupdetails.setText(details.getStartupdetails());
        }else {
            holder.startupdetails.setVisibility(View.GONE);
        }


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

