package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.*;
import ru.andrewquiz.dto.quiz.Answer;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.MappingException;

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

        dst.getAnswers().clear();
        for (Answer answerSrc : src.getAnswers()) {
            mapAnswer(answerSrc, dst);
        }

        dst.getQuestions().clear();
        for (Question questionSrc : src.getQuestions()) {
            mapQuestion(questionSrc, dst);
        }

        return dst;
    }

    private QuestionEntity mapQuestion(Question questionSrc, FullQuizEntity fullQuizDst) {

        if (questionSrc == null) {
            return null;
        }

        QuestionEntity questionDst = new QuestionEntity();

        questionDst.setFullQuiz(fullQuizDst);
        questionDst.setHint(questionSrc.getHint());
        questionDst.setQuestionNumber(questionSrc.getNumber());

        questionDst.getAnswers().clear();
        for (Long answerNumberSrc : questionSrc.getAnswers()) {
            questionDst.getAnswers().add(findAnswer(fullQuizDst.getAnswers(), answerNumberSrc));
        }

        questionDst.getKeys().clear();
        for (Long answerNumberSrc : questionSrc.getKeys()) {
            questionDst.getAnswers().add(findAnswer(fullQuizDst.getAnswers(), answerNumberSrc));
        }

        return questionDst;
    }

    private AnswerEntity mapAnswer(Answer answerSrc, FullQuizEntity dst) {

        if (answerSrc == null) {
            return null;
        }

        AnswerEntity answerDst = new AnswerEntity();

        answerDst.setFullQuiz(dst);
        answerDst.setAnswerNumber(answerSrc.getAnswerNumber());
        answerDst.setCode(answerSrc.getCode());
        answerDst.setContent(answerSrc.getContent());

        return answerDst;
    }

    private AnswerEntity findAnswer(List<AnswerEntity> answersDst, Long answerNumberSrc) {

        for (AnswerEntity answer : answersDst) {
            if (answer.getAnswerNumber().equals(answerNumberSrc)) {
                return answer;
            }
        }

        throw new MappingException("Key refers to a non-existing answer; answerNumber: " + answerNumberSrc);
    }
}
