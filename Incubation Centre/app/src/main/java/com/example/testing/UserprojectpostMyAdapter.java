package com.example.testing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


    public class  UserprojectpostMyAdapter extends RecyclerView.Adapter<UserprojectpostMyAdapter.MyViewHolder> {
        Context context;

        ArrayList<Projectitems> list;
        boolean isMentor;

        public  UserprojectpostMyAdapter(Context context, ArrayList<Projectitems> list) {
            this.context = context;
            this.list = list;
        }

        public UserprojectpostMyAdapter(Context context, ArrayList<Projectitems> list, Boolean isMentor) {
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
        public void onBindViewHolder(@NonNull UserprojectpostMyAdapter.MyViewHolder holder, int position) {
            Projectitems projectitems = list.get(position);
            holder.ptitle.setText(projectitems.getPtitle());
            holder.skill_req.setText(projectitems.getSkill_req());
            holder.branch.setText(projectitems.getBranch());
            holder.field.setText(projectitems.getField());
            holder.desc.setText(projectitems.getDesc());
            holder.email.setText(projectitems.getEmail());
            holder.ideaabstract.setText(projectitems.getIdeaabstract());

//            if (this.isMentor) {
//                holder.ideaabstract.setText(projectitems.getIdeaabstract());
//            } else {
//                holder.ideaabstract.setVisibility(View.GONE);
//            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Uppost.class);
                    i.putExtra("title",projectitems.getPtitle());
                    i.putExtra("skill",projectitems.getSkill_req());
                    i.putExtra("branches",projectitems.getBranch());
                    i.putExtra("fields",projectitems.getField());
                    i.putExtra("description",projectitems.getDesc());
                    i.putExtra("uemail",projectitems.getEmail());
                    i.putExtra("ideasabstract",projectitems.getIdeaabstract());


                    context.startActivity(i);
                    holder.ptitle.setText(projectitems.getPtitle());
                    holder.skill_req.setText(projectitems.getSkill_req());
                    holder.branch.setText(projectitems.getBranch());
                    holder.field.setText(projectitems.getField());
                    holder.desc.setText(projectitems.getDesc());
                    holder.email.setText(projectitems.getEmail());
                    holder.ideaabstract.setText(projectitems.getIdeaabstract());
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView ptitle, skill_req, branch, field, desc, email, ideaabstract;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                ptitle = itemView.findViewById(R.id.ideaptitle);
                skill_req = itemView.findViewById(R.id.ideaskills);
                branch = itemView.findViewById(R.id.ideasbranch);
                field = itemView.findViewById(R.id.ideasfield);
                desc = itemView.findViewById(R.id.ideadesc);
                email = itemView.findViewById(R.id.ideaemail);
                ideaabstract = itemView.findViewById(R.id.ideaabstract);


            }
        }


    }
