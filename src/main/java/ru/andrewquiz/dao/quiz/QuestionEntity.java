package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "questions")
public class QuestionEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Id
    @Column(name = "question_number", nullable = false)
    private Long number;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionsAnswersCorrelationEntity> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public List<QuestionsAnswersCorrelationEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionsAnswersCorrelationEntity> answers) {
        this.answers = answers;
    }

    public List<KeyEntity> getKeys() {
        return keys;
    }

    public void setKeys(List<KeyEntity> keys) {
        this.keys = keys;
    }
}
