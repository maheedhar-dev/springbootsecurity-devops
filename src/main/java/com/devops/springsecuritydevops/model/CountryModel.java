package com.devops.springsecuritydevops.model;

import java.util.ArrayList;
import java.util.List;

public class CountryModel {
    private String name;
    private String code;
    private List<StateModel> states = new ArrayList<>();

    public CountryModel(String name, String code, List<StateModel> states) {
        this.name = name;
        this.code = code;
        this.states = states;
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

    public List<StateModel> getStates() {
        return states;
    }

    public void setStates(List<StateModel> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", states=" + states +
                '}';
    }
}
