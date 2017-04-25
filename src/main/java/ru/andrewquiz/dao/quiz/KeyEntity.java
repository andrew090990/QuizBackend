package ru.andrewquiz.dao.quiz;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Andrew on 20.04.2017.
 */

@Entity
@Table(name = "`keys`")
public class KeyEntity implements Serializable {

    @EmbeddedId
    private KeyPK primaryKey = new KeyPK();

    public KeyPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(KeyPK primaryKey) {
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
            getPrimaryKey().getQuestion().removeKey(this, false);
        }

        getPrimaryKey().setQuestion(question);

        if (question != null && updateReference) {
            question.addKey(this, false);
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

        KeyEntity that = (KeyEntity)o;

        return getAnswerId().equals(that.getAnswerId())
                && getQuestion().equals(that.getQuestion());
    }

    @Override
    public int hashCode() {
        int result = getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
        return result;
    }

}
