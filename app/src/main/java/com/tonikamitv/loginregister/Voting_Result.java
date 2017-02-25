package com.tonikamitv.loginregister;

/**
 * Created by chahat on 13/3/16.
 */
public class Voting_Result {
    int voted;
    int optionASent, optionBSent;
    int age;


    public Voting_Result(int age, int optionASent, int optionBSent, int voted) {

        this.age = age;
        this.optionASent = optionASent;
        this.optionBSent = optionBSent;
        this.voted = voted;


    }

    public Voting_Result(int optionASent, int optionBSent) {
        this(-1, 0, 0, -1);
    }
}
