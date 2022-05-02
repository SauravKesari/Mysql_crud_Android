package com.example.myapplication;

public class List_data {
    private String name,email,pass,gender;
    private int id;

    public List_data(int id, String name, String email, String pass, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
