package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.*;
import ru.andrewquiz.dto.quiz.Answer;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class FullQuizEntityToDtoMapper extends AbstractMapper<FullQuizEntity, FullQuiz> {

    private QuizEntityToDtoMapper baseMapper;

    @Autowired
    public FullQuizEntityToDtoMapper(QuizEntityToDtoMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    protected FullQuiz perfomMapping(FullQuizEntity src) {
        return perfomMapping(src, new FullQuiz());
    }

    @Override
    protected FullQuiz perfomMapping(FullQuizEntity src, FullQuiz dst) {

        baseMapper.perfomMapping(src, dst);

        dst.setContent(src.getContent());
        dst.setInstructions(src.getInstructions());
        dst.setIntroduction(src.getIntroduction());

        dst.getQuestions().clear();
        for (QuestionEntity questionSrc : src.getQuestions()) {
           dst.getQuestions().add(mapQuestion(questionSrc));
        }

        dst.getAnswers().clear();
        for (AnswerEntity answerSrc : src.getAnswers()) {
            dst.getAnswers().add(mapAnswer(answerSrc));
        }

        return dst;
    }

    private Question mapQuestion(QuestionEntity questionSrc) {

        if (questionSrc == null) {
            return null;
        }

        Question questionDst = new Question();

        questionDst.setHint(questionSrc.getHint());
        questionDst.setNumber(questionSrc.getNumber());

        questionDst.getAnswers().clear();
        for (QuestionsAnswersCorrelationEntity answerSrc : questionSrc.getAnswers()) {
            questionDst.getAnswers().add(answerSrc != null ? answerSrc.getAnswerId() : null);
        }

        questionDst.getKeys().clear();
        for (KeyEntity keySrc : questionSrc.getKeys()) {
            questionDst.getKeys().add(keySrc != null ? keySrc.getAnswerId() : null);
        }

        return questionDst;
    }

    private Answer mapAnswer(AnswerEntity answerSrc) {

        if (answerSrc == null) {
            return null;
        }

        Answer answerDst = new Answer();

        answerDst.setId(answerSrc.getId());
        answerDst.setCode(answerSrc.getCode());
        answerDst.setContent(answerSrc.getContent());

        return answerDst;
    }
}
