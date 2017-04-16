package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 16.04.2017.
 */

public class FullQuiz extends  Quiz {

    private String instructions;

    private String introduction;

    private String content;

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

}
