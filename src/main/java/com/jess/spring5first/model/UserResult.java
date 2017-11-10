package com.jess.spring5first.model;

public class UserResult extends Base {

    private String result;

    public UserResult(String name, String result) {
        super(name);
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
