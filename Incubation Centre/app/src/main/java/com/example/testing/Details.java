package com.example.testing;

public class Details {
    String ptitle, skill_req, desc,email,startupdetails;

    public Details() {
    }

        public Details(String title, String Skill, String Description,String Email, String Startupdetails){
        this.ptitle=title;
        this.skill_req=Skill;
        this.desc=Description;
        this. email=Email;
        this.startupdetails=Startupdetails;

    }

    public String getPtitle() {
        return ptitle;
    }

    public String getSkill_req() {
        return skill_req;
    }

    public String getDesc() {
        return desc;
    }
    public String getEmail() { return email; }

    public String getStartupdetails() {
        return startupdetails;
    }




}
