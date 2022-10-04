package com.example.bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class Attemp {

    private Integer attemp;
    private Integer users_num;
    private Integer bulls;
    private Integer cows;


    public Integer getAttemp() {
        return attemp;
    }

    public void setAttemp(Integer attemp) {
        this.attemp = attemp;
    }

    public Integer getUsers_num() {
        return users_num;
    }

    public void setUsers_num(Integer users_num) {
        this.users_num = users_num;
    }

    public Integer getBulls() {
        return bulls;
    }

    public void setBulls(Integer bulls) {
        this.bulls = bulls;
    }

    public Integer getCows() {
        return cows;
    }

    public void setCows(Integer cows) {
        this.cows = cows;
    }

    @Override
    public String toString() {
        return  attemp +
                "\t\t\t" + users_num +
                "\t\t\t" + bulls +
                " bulls, " + cows +
                " cows";
    }

    public Attemp(Integer attemp, Integer users_num, Integer bulls, Integer cows) {
        this.attemp = attemp;
        this.users_num = users_num;
        this.bulls = bulls;
        this.cows = cows;
    }


}
