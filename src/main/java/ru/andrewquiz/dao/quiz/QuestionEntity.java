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
        setFullQuiz(fullQuiz, true);
    }

    public void setFullQuiz(FullQuizEntity fullQuiz, boolean updateReference) {
        if (this.fullQuiz!= null) {
            this.fullQuiz.removeQuestion(this, false);
        }

        this.fullQuiz = fullQuiz;

        if (fullQuiz != null && updateReference) {
            fullQuiz.addQuestion(this, false);
        }
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

    public void addAnswer(QuestionsAnswersCorrelationEntity answer) {
        addAnswer(answer, true);
    }

    public void addAnswer(QuestionsAnswersCorrelationEntity answer, boolean updateReference) {
        if (answer == null) {
            return;
        }

        if (answers.contains(answer)) {
            answers.set(answers.indexOf(answer), answer);
        } else {
            answers.add(answer);
        }

        if (updateReference) {
            answer.setQuestion(this, false);
        }
    }

    public void removeAnswer(QuestionsAnswersCorrelationEntity answer) {
        removeAnswer(answer, true);
    }

    public void removeAnswer(QuestionsAnswersCorrelationEntity answer, boolean updateReference) {
        if (answer == null) {
            return;
        }

        answers.remove(answer);

        if (updateReference) {
            answer.setQuestion(null, false);
        }
    }

    public List<KeyEntity> getKeys() {
        return keys;
    }

    public void setKeys(List<KeyEntity> keys) {
        this.keys = keys;
    }

    public void addKey(KeyEntity key) {
        addKey(key, true);
    }

    public void addKey(KeyEntity key, boolean updateReference) {
        if (key == null) {
            return;
        }

        if (keys.contains(key)) {
            keys.set(keys.indexOf(key), key);
        } else {
            keys.add(key);
        }

        if (updateReference) {
            key.setQuestion(this, false);
        }
    }

    public void removeKey(KeyEntity key) {
        removeKey(key, true);
    }

    public void removeKey(KeyEntity key, boolean updateReference) {
        if (key == null) {
            return;
        }

        keys.remove(key);

        if (updateReference) {
            key.setQuestion(null, false);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || getNumber() == null || getFullQuiz() == null) {
            return false;
        }

        QuestionEntity that = (QuestionEntity)o;

        return getNumber().equals(that.getNumber())
                && getFullQuiz().equals(that.getFullQuiz());
    }

    @Override
    public int hashCode() {
        int result = fullQuiz != null ? fullQuiz.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
