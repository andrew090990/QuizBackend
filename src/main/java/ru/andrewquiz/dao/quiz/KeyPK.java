package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrew on 25.04.2017.
 */

@Embeddable
public class KeyPK implements Serializable {

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id"),
            @JoinColumn(name = "question_number", referencedColumnName = "question_number")
    })
    private QuestionEntity question;

    @Column(name = "answer_id")
    private Long answerId;

    protected QuestionEntity getQuestion() {
        return question;
    }

    protected void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    protected Long getAnswerId() {
        return answerId;
    }

    protected void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyPK keyPK = (KeyPK) o;

        if (question == null || !question.equals(keyPK.question)) {
            return false;
        }

        if (answerId == null || !answerId.equals(keyPK.answerId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        return result;
    }
}
