package ru.andrewquiz.dto.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public class Question {

    private Long questionNumber;

    private String hint;

    private List<Long> answers = new ArrayList<Long>();

    private List<Long> keys = new ArrayList<Long>();

    public Long getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<Long> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Long> answers) {
        this.answers = answers;
    }

    public List<Long> getKeys() {
        return keys;
    }

    public void setKeys(List<Long> keys) {
        this.keys = keys;
    }
}
