package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrew on 21.04.2017.
 */

@Entity
@Table(name = "questions_answers_correlation")
public class QuestionsAnswersCorrelationEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id"),
            @JoinColumn(name = "question_number", referencedColumnName = "question_number")
    })
    private QuestionEntity question;

    @Id
    @Column(name = "answer_id")
    private Long answerId;

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        setQuestion(question, true);
    }

    public void setQuestion(QuestionEntity question, boolean updateReference) {
        if (this.question!= null) {
            this.question.removeAnswer(this, false);
        }

        this.question = question;

        if (question != null && updateReference) {
            question.addAnswer(this, false);
        }
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || getAnswerId() == null || getQuestion() == null) {
            return false;
        }

        QuestionsAnswersCorrelationEntity that = (QuestionsAnswersCorrelationEntity)o;

        return getAnswerId().equals(that.getAnswerId())
                && getQuestion().equals(that.getQuestion());
    }

    @Override
    public int hashCode() {
        int result = answerId != null ? answerId.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
