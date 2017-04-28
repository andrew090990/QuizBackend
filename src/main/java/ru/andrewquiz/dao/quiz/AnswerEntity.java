package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "answers", uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_id", "answer_number"}))
public class AnswerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Column(name = "answer_number", nullable = false)
    private Long answerNumber;

    @Column(name = "content")
    private String content;

    @Column(name = "code")
    private String code;

    public FullQuizEntity getFullQuiz() {
        return this.fullQuiz;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        setFullQuiz(fullQuiz, true);
    }

    public Long getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Long answerNumber) {
        this.answerNumber = answerNumber;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz, boolean updateReference) {
        if (this.fullQuiz != null) {
            this.fullQuiz.removeAnswer(this, false);
        }

        this.fullQuiz = fullQuiz;

        if (fullQuiz != null && updateReference) {
            fullQuiz.addAnswer(this, false);
        }
    }

    public Long getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
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

        if (o == null || getClass() != o.getClass() || this.fullQuiz == null || this.answerNumber == null) {
            return false;
        }

        AnswerEntity that = (AnswerEntity)o;

        return this.fullQuiz.equals(that.fullQuiz) && this.answerNumber.equals(that.answerNumber);
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (answerNumber != null ? answerNumber.hashCode() : 0);

        return result;
    }
}
