package com.devops.springsecuritydevops.model;

public class StateModel {

    private String name;
    private String code;

    public StateModel(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StateModel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
