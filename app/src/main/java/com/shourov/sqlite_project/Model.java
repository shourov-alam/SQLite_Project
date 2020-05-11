package com.shourov.sqlite_project;

import java.io.Serializable;

public class Model implements Serializable {
    String name,address;
    int age,id;

    public Model(String name, int age,String address) {
        this.name = name;
        this.address = address;
        this.age = age;


    }

    public Model(String name, String address, int age, int id) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
