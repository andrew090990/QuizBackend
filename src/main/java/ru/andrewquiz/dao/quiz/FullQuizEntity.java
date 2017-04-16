package ru.andrewquiz.dao.quiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "quiz_contents")
@PrimaryKeyJoinColumn (name="quiz_id")
public class FullQuizEntity extends QuizEntity {

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "content")
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
