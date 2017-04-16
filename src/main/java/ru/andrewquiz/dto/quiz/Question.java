package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 16.04.2017.
 */

public class Question {

    private Long number;

    private String hint;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
