package com.jess.spring5first.model;

public class Base {

    String name;

    public Base() {}

    public Base(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
