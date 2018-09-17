package ru.andrewquiz.dto.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public class FullQuiz extends  Quiz {

    private String instructions;

    private String introduction;

    private String content;

    private List<Question> questions = new ArrayList<Question>();

    private List<Answer> answers = new ArrayList<Answer>();

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
