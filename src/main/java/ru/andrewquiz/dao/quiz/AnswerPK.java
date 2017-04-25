package ru.andrewquiz.dao.quiz;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Andrew on 25.04.2017.
 */

@Embeddable
public class AnswerPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Column(name = "answer_id", nullable = false)
    private Long answerId;

    public FullQuizEntity getFullQuiz() {
        return fullQuiz;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        this.fullQuiz = fullQuiz;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerPK answerPK = (AnswerPK) o;

        if (fullQuiz == null || fullQuiz.equals(answerPK.fullQuiz)) {
            return false;
        }

        if (answerId == null || answerId.equals(answerPK.answerId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        return result;
    }
}
