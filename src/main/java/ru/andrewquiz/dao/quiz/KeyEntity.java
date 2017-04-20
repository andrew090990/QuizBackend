package ru.andrewquiz.dao.quiz;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Created by Andrew on 20.04.2017.
 */


public class KeyEntity {

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id"),
            @JoinColumn(name = "question_number", referencedColumnName = "number")
    })
    private QuestionEntity question;

    @Column(name = "answer_id")
    private Long answerId;

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
