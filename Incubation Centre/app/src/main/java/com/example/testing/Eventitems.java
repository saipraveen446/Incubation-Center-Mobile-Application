package com.example.testing;

public class Eventitems {
    String event_title,event_email,event_desc;
    public Eventitems(){

    }

    public Eventitems(String event_title,String event_email, String event_desc) {
        this.event_title = event_title;
        this.event_desc = event_desc;
        this.event_email=event_email;
    }

    public String getEvent_title() {
        return event_title;
    }

    public String getEvent_email() {
        return event_email;
    }

    public String getEvent_desc() {
        return event_desc;
    }


}
