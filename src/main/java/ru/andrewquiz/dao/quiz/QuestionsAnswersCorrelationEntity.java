package ru.andrewquiz.dao.quiz;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Andrew on 21.04.2017.
 */

@Entity
@Table(name = "questions_answers_correlation")
public class QuestionsAnswersCorrelationEntity implements Serializable {

    @EmbeddedId
    private QuestionsAnswersCorrelationPK primaryKey = new QuestionsAnswersCorrelationPK();

    public QuestionsAnswersCorrelationPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(QuestionsAnswersCorrelationPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public QuestionEntity getQuestion() {
        return getPrimaryKey().getQuestion();
    }

    public void setQuestion(QuestionEntity question) {
        setQuestion(question, true);
    }

    public void setQuestion(QuestionEntity question, boolean updateReference) {
        if (getPrimaryKey().getQuestion() != null) {
            getPrimaryKey().getQuestion().removeAnswer(this, false);
        }

        getPrimaryKey().setQuestion(question);

        if (question != null && updateReference) {
            question.addAnswer(this, false);
        }
    }

    public Long getAnswerId() {
        return getPrimaryKey().getAnswerId();
    }

    public void setAnswerId(Long answerId) {
        getPrimaryKey().setAnswerId(answerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || getPrimaryKey() == null) {
            return false;
        }

        QuestionsAnswersCorrelationEntity that = (QuestionsAnswersCorrelationEntity)o;

        return getPrimaryKey().equals(that.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        int result = getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
        return result;
    }
}
