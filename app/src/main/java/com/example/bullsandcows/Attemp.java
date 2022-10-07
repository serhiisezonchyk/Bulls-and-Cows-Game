package com.example.bullsandcows;

public class Attemp {
    private Integer attemp;
    private Integer users_num;
    private Integer bulls;
    private Integer cows;
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
