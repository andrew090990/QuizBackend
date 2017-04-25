package ru.andrewquiz.dao.quiz;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "answers")
public class AnswerEntity implements Serializable {

    @EmbeddedId
    private AnswerPK primaryKey = new AnswerPK();

    @Column(name = "content")
    private String content;

    @Column(name = "code")
    private String code;

    public AnswerPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AnswerPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public FullQuizEntity getFullQuiz() {
        return getPrimaryKey().getFullQuiz();
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        setFullQuiz(fullQuiz, true);
    }

    public void setFullQuiz(FullQuizEntity fullQuiz, boolean updateReference) {
        if (getPrimaryKey().getFullQuiz()!= null) {
            getPrimaryKey().getFullQuiz().removeAnswer(this, false);
        }

        getPrimaryKey().setFullQuiz(fullQuiz);

        if (fullQuiz != null && updateReference) {
            fullQuiz.addAnswer(this, false);
        }
    }

    public Long getAnswerId() {
        return getPrimaryKey().getAnswerId();
    }

    public void setAnswerId(Long id) {
        getPrimaryKey().setAnswerId(id);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || getPrimaryKey() == null) {
            return false;
        }

        AnswerEntity that = (AnswerEntity)o;

        return getPrimaryKey().equals(that.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        int result = getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
        return result;
    }
}
