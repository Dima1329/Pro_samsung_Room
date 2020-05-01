package com.example.pro_samsung;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Question implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private String question;
    private String variant1;
    private String variant2;
    private String variant3;
    private String variant4;
    private int answer;

    public Question( String question, String variant1, String variant2, String variant3, String variant4, int answer) {
        this.question = question;
        this.variant1 = variant1;
        this.variant2 = variant2;
        this.variant3 = variant3;
        this.variant4 = variant4;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getVariant1() {
        return variant1;
    }

    public String getVariant2() {
        return variant2;
    }

    public String getVariant3() {
        return variant3;
    }

    public int  getAnswer() {
        return answer;
    }

    public String getVariant4() {
        return variant4;
    }


}
