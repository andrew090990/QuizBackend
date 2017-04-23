package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "answers")
public class AnswerEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "code")
    private String code;

    public FullQuizEntity getFullQuiz() {
        return fullQuiz;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        setFullQuiz(fullQuiz, true);
    }

    public void setFullQuiz(FullQuizEntity fullQuiz, boolean updateReference) {
        if (this.fullQuiz!= null) {
            this.fullQuiz.removeAnswer(this, false);
        }

        this.fullQuiz = fullQuiz;

        if (fullQuiz != null && updateReference) {
            fullQuiz.addAnswer(this, false);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        if (o == null || getClass() != o.getClass() || getId() == null || getFullQuiz() == null) {
            return false;
        }

        AnswerEntity that = (AnswerEntity)o;

        return getId().equals(that.getId())
                && getFullQuiz().equals(that.getFullQuiz());
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
