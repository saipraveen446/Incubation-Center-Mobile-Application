package com.example.testing;

public class Mentoritems {
    String  ptitle,mentorEmail,qualifications,designat,skills,fields,desc;
    public Mentoritems(){

    }

    public Mentoritems(String ptitle, String mentorEmail, String qualifications,String designat,String skills, String fields, String desc) {
        this.ptitle = ptitle;
        this.mentorEmail = mentorEmail;
        this.qualifications = qualifications;
        this.designat=designat;
        this.skills=skills;
        this.fields = fields;
        this.desc = desc;
    }

    public String getPtitle() {
        return ptitle;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }


    public String getQualifications() {
        return qualifications;
    }

    public String getDesignat() {
        return designat;
    }

    public String getSkills() {
        return skills;
    }

    public String getFields() {
        return fields;
    }

    public String getDesc() {
        return desc;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public void setDesignat(String designat) {
        this.designat = designat;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
