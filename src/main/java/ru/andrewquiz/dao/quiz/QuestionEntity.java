package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "questions")
public class QuestionEntity implements Serializable {

    @EmbeddedId
    private QuestionPK primaryKey = new QuestionPK();

    @OneToMany(mappedBy = "primaryKey.question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionsAnswersCorrelationEntity> answers = new ArrayList<QuestionsAnswersCorrelationEntity>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeyEntity> keys = new ArrayList<KeyEntity>();

    @Column(name = "hint")
    private String hint;

    public QuestionPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(QuestionPK primaryKey) {
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
            getPrimaryKey().getFullQuiz().removeQuestion(this, false);
        }

        getPrimaryKey().setFullQuiz(fullQuiz);

        if (fullQuiz != null && updateReference) {
            fullQuiz.addQuestion(this, false);
        }
    }

    public Long getQuestionNumber() {
        return getPrimaryKey().getQuestionNumber();
    }

    public void setQuestionNumber(Long number) {
        getPrimaryKey().setQuestionNumber(number);
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

        if (o == null || getClass() != o.getClass() || getPrimaryKey() == null) {
            return false;
        }

        QuestionEntity that = (QuestionEntity)o;

        return getPrimaryKey().equals(that.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        int result = getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
        return result;
    }
}
