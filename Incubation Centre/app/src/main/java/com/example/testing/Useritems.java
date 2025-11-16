package com.example.testing;

public class Useritems {
    String UserId,Email,Name, Skill,Dept,IdNUm,Interestedarea;
    Boolean Mentor;
//    public Useritems(String email, String name, String skill, String department, String interestedarea, String idnum) {
//    }

    public Useritems(String userId, String email, String name, String skill,String department, String idNUm,String interestedarea, Boolean mentor) {
        UserId = userId;
        Email = email;
        Name = name;
        Skill = skill;
        IdNUm = idNUm;
        Dept=department;

        Interestedarea=interestedarea;
        Mentor = mentor;
    }

    public Useritems(String email, String name, String skill,String department,String interestedarea ,String idNUm) {
        Email = email;
        Name = name;
        Skill = skill;
        IdNUm = idNUm;
        Dept=department;
        Interestedarea=interestedarea;

    }

    public String getUserId() {
        return UserId;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getSkill() {
        return Skill;
    }

    public String getDept() {
        return Dept;
    }

    public String getIdNUm() {
        return IdNUm;
    }

    public Boolean getMentor() {
        return Mentor;
    }

    public String getInterestedarea() {
        return Interestedarea;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public void setIdNUm(String idNUm) {
        IdNUm = idNUm;
    }

    public void setMentor(Boolean mentor) {
        Mentor = mentor;
    }

    public void setInterestedarea(String interestedarea) {
        Interestedarea = interestedarea;
    }
}
