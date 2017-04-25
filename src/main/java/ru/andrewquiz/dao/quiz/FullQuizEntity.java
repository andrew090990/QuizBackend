package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Entity
@Table(name = "quiz_contents")
@PrimaryKeyJoinColumn (name="quiz_id")
public class FullQuizEntity extends QuizEntity {

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "fullQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<QuestionEntity>();

    @OneToMany(mappedBy = "primaryKey.fullQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEntity> answers = new ArrayList<AnswerEntity>();

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionEntity question) {
        addQuestion(question, true);
    }

    public void addQuestion(QuestionEntity question, boolean updateReference) {
        if (question == null) {
            return;
        }

        if (questions.contains(question)) {
            questions.set(questions.indexOf(question), question);
        } else {
            questions.add(question);
        }

        if (updateReference) {
            question.setFullQuiz(this, false);
        }
    }

    public void removeQuestion(QuestionEntity question) {
        removeQuestion(question, true);
    }

    public void removeQuestion(QuestionEntity question, boolean updateReference) {
        if (question == null) {
            return;
        }

        questions.remove(question);

        if (updateReference) {
            question.setFullQuiz(null, false);
        }
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public void addAnswer(AnswerEntity answer) {
        addAnswer(answer, true);
    }

    public void addAnswer(AnswerEntity answer, boolean updateReference) {
        if (answer == null) {
            return;
        }

        if (answers.contains(answer)) {
            answers.set(answers.indexOf(answer), answer);
        } else {
            answers.add(answer);
        }

        if (updateReference) {
            answer.setFullQuiz(this, false);
        }
    }

    public void removeAnswer(AnswerEntity answer) {
        removeAnswer(answer, true);
    }

    public void removeAnswer(AnswerEntity answer, boolean updateReference) {
        if (answer == null) {
            return;
        }

        answers.remove(answer);

        if (updateReference) {
            answer.setFullQuiz(null, false);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || getId() == null) {
            return false;
        }

        return getId().equals(((FullQuizEntity)o).getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }
}
