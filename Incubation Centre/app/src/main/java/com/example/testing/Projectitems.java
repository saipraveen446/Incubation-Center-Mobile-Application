package com.example.testing;

public class Projectitems {
    String ptitle, skill_req,branch,field,email,desc,ideaabstract;


    public Projectitems() {
    }

    public Projectitems(String ptitle, String skill_req, String branch, String field,  String desc,String email, String ideaabstract) {
        this.ptitle = ptitle;
        this.skill_req = skill_req;
        this.branch = branch;
        this.field = field;
        this.desc = desc;
        this.email = email;
        this.ideaabstract = ideaabstract;
    }


    public String getPtitle() {
        return ptitle;
    }

    public String getSkill_req() {
        return skill_req;
    }

    public String getBranch() {
        return branch;
    }

    public String getField() {
        return field;
    }



    public String getDesc() {
        return desc;
    }
    public String getEmail() {
        return email;
    }

    public String getIdeaabstract() {
        return ideaabstract;
    }
}
