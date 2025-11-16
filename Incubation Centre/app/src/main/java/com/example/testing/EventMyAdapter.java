package com.example.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventMyAdapter extends RecyclerView.Adapter<EventMyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Eventitems> list;

    public EventMyAdapter(Context context, ArrayList<Eventitems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eventsdetails, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Eventitems eventitems = list.get(position);
        holder.event_title.setText(eventitems.getEvent_title());
        holder.event_email.setText(eventitems.getEvent_email());
        holder.event_desc.setText(eventitems.getEvent_desc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView event_title,event_email,event_desc;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            event_title = itemView.findViewById(R.id.eventptitle);
            event_email =itemView.findViewById(R.id.eventemail);
            event_desc =itemView.findViewById(R.id.eventskills);


        }
    }
}

