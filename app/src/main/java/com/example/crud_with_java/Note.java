package com.example.crud_with_java;

public class Note {
    int id;
    String name;
    String email;
    String mobile;

    public Note(int id, String name, String email, String mobile) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.mobile=mobile;
    }

    public int id() {
        return id;
    }
}
