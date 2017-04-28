package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.AnswerEntity;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dao.quiz.QuestionEntity;
import ru.andrewquiz.dto.quiz.Answer;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.MappingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class FullQuizDtoToEntityMapper extends AbstractMapper<FullQuiz, FullQuizEntity> {

    private QuizDtoToEntityMapper baseMapper;

    private SuitRepository suitRepo;

    @Autowired
    public FullQuizDtoToEntityMapper(QuizDtoToEntityMapper baseMapper, SuitRepository suitRepo) {
        this.suitRepo = suitRepo;
        this.baseMapper = baseMapper;
    }

    @Override
    protected FullQuizEntity performMapping(FullQuiz src) {
        return performMapping(src, new FullQuizEntity());
    }

    @Override
    protected FullQuizEntity performMapping(FullQuiz src, FullQuizEntity dst) {

        baseMapper.performMapping(src, dst);

        dst.setContent(src.getContent());
        dst.setInstructions(src.getInstructions());
        dst.setIntroduction(src.getIntroduction());

        List<AnswerEntity> oldAnswers = new ArrayList<AnswerEntity>(dst.getAnswers());
        dst.getAnswers().clear();
        for (Answer answerSrc : src.getAnswers()) {
            AnswerEntity oldAnswer = findAnswer(oldAnswers, answerSrc.getAnswerNumber());
            if (oldAnswer == null) {
                mapAnswer(answerSrc, new AnswerEntity(), dst);
            } else {
                mapAnswer(answerSrc, oldAnswer, dst);
            }
        }

        List<QuestionEntity> oldQuestions = new ArrayList<QuestionEntity>(dst.getQuestions());
        dst.getQuestions().clear();
        for (Question questionSrc : src.getQuestions()) {
            QuestionEntity oldQuestion = findQuestion(oldQuestions, questionSrc.getQuestionNumber());
            if (oldQuestion == null) {
                mapQuestion(questionSrc, new QuestionEntity(), dst);
            } else {
                mapQuestion(questionSrc, oldQuestion, dst);
            }
        }

        return dst;
    }

    private QuestionEntity mapQuestion(Question questionSrc, QuestionEntity questionDst, FullQuizEntity fullQuizDst) {

        if (questionSrc == null) {
            return null;
        }

        questionDst.setFullQuiz(fullQuizDst);
        questionDst.setHint(questionSrc.getHint());
        questionDst.setQuestionNumber(questionSrc.getQuestionNumber());

        questionDst.getAnswers().clear();
        for (Long answerNumberSrc : questionSrc.getAnswers()) {
            AnswerEntity answer = findAnswer(fullQuizDst.getAnswers(), answerNumberSrc);
            if (answer == null) {
                throw new MappingException("Answer option refers to a non-existing answer; answerNumber: " + answerNumberSrc);
            } else {
                questionDst.getAnswers().add(answer);
            }
        }

        questionDst.getKeys().clear();
        for (Long answerNumberSrc : questionSrc.getKeys()) {
            AnswerEntity answer = findAnswer(fullQuizDst.getAnswers(), answerNumberSrc);
            if (answer == null) {
                throw new MappingException("Key refers to a non-existing answer; answerNumber: " + answerNumberSrc);
            } else {
                questionDst.getKeys().add(answer);
            }
        }

        return questionDst;
    }

    private AnswerEntity mapAnswer(Answer answerSrc, AnswerEntity answerDst, FullQuizEntity dst) {

        if (answerSrc == null) {
            return null;
        }

        answerDst.setFullQuiz(dst);
        answerDst.setAnswerNumber(answerSrc.getAnswerNumber());
        answerDst.setCode(answerSrc.getCode());
        answerDst.setContent(answerSrc.getContent());

        return answerDst;
    }

    private QuestionEntity findQuestion(List<QuestionEntity> questionsDst, Long questionNumberSrc) {

        for (QuestionEntity question : questionsDst) {
            if (question.getQuestionNumber().equals(questionNumberSrc)) {
                return question;
            }
        }

        return null;
    }

    private AnswerEntity findAnswer(List<AnswerEntity> answersDst, Long answerNumberSrc) {

        for (AnswerEntity answer : answersDst) {
            if (answer.getAnswerNumber().equals(answerNumberSrc)) {
                return answer;
            }
        }

        return null;
    }
}
