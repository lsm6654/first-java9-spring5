package com.jess.spring5first.model;

import java.io.Serializable;

public class User extends Base implements Serializable {

    private int age;

    public User() {
        super();

    }

    public User(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name : "+name +", age : " +age;
    }
}
