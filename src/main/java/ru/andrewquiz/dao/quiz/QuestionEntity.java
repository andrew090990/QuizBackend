package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "questions", uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_id", "question_number"}))
public class QuestionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private FullQuizEntity fullQuiz;

    @Column(name = "question_number", nullable = false)
    private Long questionNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "questions_answers",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "answer_id"))
    private List<AnswerEntity> answers = new ArrayList<AnswerEntity>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "`keys`",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "answer_id"))//TODO orphan removal
    private List<AnswerEntity> keys = new ArrayList<AnswerEntity>();

    @Column(name = "hint")
    private String hint;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public FullQuizEntity getFullQuiz() {
        return this.fullQuiz;
    }

    public void setFullQuiz(FullQuizEntity fullQuiz) {
        setFullQuiz(fullQuiz, true);
    }

    public void setFullQuiz(FullQuizEntity fullQuiz, boolean updateReference) {
        if (this.fullQuiz != null) {
            this.fullQuiz.removeQuestion(this, false);
        }

        this.fullQuiz = fullQuiz;

        if (fullQuiz != null && updateReference) {
            fullQuiz.addQuestion(this, false);
        }
    }

    public Long getQuestionNumber() {
        return this.questionNumber;
    }

    public void setQuestionNumber(Long number) {
        this.questionNumber = number;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public List<AnswerEntity> getKeys() {
        return keys;
    }

    public void setKeys(List<AnswerEntity> keys) {
        this.keys = keys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || this.fullQuiz == null || this.questionNumber == null) {
            return false;
        }

        QuestionEntity that = (QuestionEntity)o;

        return this.fullQuiz.equals(that.fullQuiz) && this.questionNumber.equals(that.questionNumber);
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (questionNumber != null ? questionNumber.hashCode() : 0);

        return result;
    }
}
