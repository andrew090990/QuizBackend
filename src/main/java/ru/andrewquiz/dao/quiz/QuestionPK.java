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
public class QuestionPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Column(name = "question_number", nullable = false)
    private Long questionNumber;

    protected FullQuizEntity getFullQuiz() {
        return fullQuiz;
    }

    protected void setFullQuiz(FullQuizEntity fullQuiz) {
        this.fullQuiz = fullQuiz;
    }

    protected Long getQuestionNumber() {
        return questionNumber;
    }

    protected void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionPK questionPK = (QuestionPK) o;

        if (fullQuiz == null || !fullQuiz.equals(questionPK.fullQuiz)) {
            return false;
        }

        if (questionNumber == null || !questionNumber.equals(questionPK.questionNumber)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (questionNumber != null ? questionNumber.hashCode() : 0);
        return result;
    }
}
