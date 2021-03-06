package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.AnswerEntity;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dao.quiz.QuestionEntity;
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
    protected FullQuiz performMapping(FullQuizEntity src) {
        return performMapping(src, new FullQuiz());
    }

    @Override
    protected FullQuiz performMapping(FullQuizEntity src, FullQuiz dst) {

        baseMapper.performMapping(src, dst);

        dst.setContent(src.getContent());
        dst.setInstructions(src.getInstructions());
        dst.setIntroduction(src.getIntroduction());

        dst.getAnswers().clear();
        for (AnswerEntity answerSrc : src.getAnswers()) {
            dst.getAnswers().add(mapAnswer(answerSrc));
        }

        dst.getQuestions().clear();
        for (QuestionEntity questionSrc : src.getQuestions()) {
           dst.getQuestions().add(mapQuestion(questionSrc));
        }

        return dst;
    }

    private Question mapQuestion(QuestionEntity questionSrc) {

        if (questionSrc == null) {
            return null;
        }

        Question questionDst = new Question();

        questionDst.setHint(questionSrc.getHint());
        questionDst.setQuestionNumber(questionSrc.getQuestionNumber());

        questionDst.getAnswers().clear();
        for (AnswerEntity answerSrc : questionSrc.getAnswers()) {
            questionDst.getAnswers().add(answerSrc != null ? answerSrc.getAnswerNumber() : null);
        }

        questionDst.getKeys().clear();
        for (AnswerEntity keySrc : questionSrc.getKeys()) {
            questionDst.getKeys().add(keySrc != null ? keySrc.getAnswerNumber() : null);
        }

        return questionDst;
    }

    private Answer mapAnswer(AnswerEntity answerSrc) {

        if (answerSrc == null) {
            return null;
        }

        Answer answerDst = new Answer();

        answerDst.setAnswerNumber(answerSrc.getAnswerNumber());
        answerDst.setCode(answerSrc.getCode());
        answerDst.setContent(answerSrc.getContent());

        return answerDst;
    }
}
