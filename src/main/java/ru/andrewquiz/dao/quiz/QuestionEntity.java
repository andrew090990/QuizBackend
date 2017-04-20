package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "questions")
public class QuestionEntity implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Id
    @Column(name = "number", nullable = false)
    private Long number;

    @OneToMany(mappedBy = "question")
    private List<KeyEntity> keys;

    @Column(name = "hint")
    private String hint;

    public FullQuizEntity getFullQuiz() {
        return fullQuiz;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        this.fullQuiz = fullQuiz;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
