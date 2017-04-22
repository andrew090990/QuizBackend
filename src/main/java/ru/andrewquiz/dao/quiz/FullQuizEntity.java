package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
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
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "fullQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEntity> answers;

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

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    @Override
    public void attachChildrenToParent() {
        for (QuestionEntity questionEntity : questions) {
            questionEntity.setFullQuiz(this);

            for (QuestionsAnswersCorrelationEntity questionsAnswersCorrelationEntity : questionEntity.getAnswers()) {
                questionsAnswersCorrelationEntity.setQuestion(questionEntity);
            }

            for (KeyEntity keyEntity : questionEntity.getKeys()) {
                keyEntity.setQuestion(questionEntity);
            }
        }

        for (AnswerEntity answerEntity : answers) {
            answerEntity.setFullQuiz(this);
        }
    }
}
