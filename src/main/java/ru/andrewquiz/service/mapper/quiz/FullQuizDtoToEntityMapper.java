package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.*;
import ru.andrewquiz.dto.quiz.Answer;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.mapper.AbstractMapper;

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
    protected FullQuizEntity perfomMapping(FullQuiz src) {
        return perfomMapping(src, new FullQuizEntity());
    }

    @Override
    protected FullQuizEntity perfomMapping(FullQuiz src, FullQuizEntity dst) {

        baseMapper.perfomMapping(src, dst);

        dst.setContent(src.getContent());
        dst.setInstructions(src.getInstructions());
        dst.setIntroduction(src.getIntroduction());

        dst.getQuestions().clear();
        for (Question questionSrc : src.getQuestions()) {
            mapQuestion(questionSrc, dst);
        }

        dst.getAnswers().clear();
        for (Answer answerSrc : src.getAnswers()) {
            mapAnswer(answerSrc, dst);
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
        questionDst.setNumber(questionSrc.getNumber());

        questionDst.getAnswers().clear();
        for (Long answerIdSrc : questionSrc.getAnswers()) {
            mapQuestionsAnswersCorrelation(answerIdSrc, questionDst);
        }

        questionDst.getKeys().clear();
        for (Long keyAnswerIdSrc : questionSrc.getKeys()) {
            mapKey(keyAnswerIdSrc, questionDst);
        }

        return questionDst;
    }

    private QuestionsAnswersCorrelationEntity mapQuestionsAnswersCorrelation(Long answerIdSrc, QuestionEntity questionDst) {

        if (answerIdSrc == null) {
            return null;
        }

        QuestionsAnswersCorrelationEntity answerDst = new QuestionsAnswersCorrelationEntity();

        answerDst.setAnswerId(answerIdSrc);
        answerDst.setQuestion(questionDst);

        return answerDst;
    }

    private KeyEntity mapKey(Long keyAnswerIdSrc, QuestionEntity questionDst) {

        if (keyAnswerIdSrc == null) {
            return null;
        }

        KeyEntity keyDst = new KeyEntity();

        keyDst.setAnswerId(keyAnswerIdSrc);
        keyDst.setQuestion(questionDst);

        return keyDst;
    }

    private AnswerEntity mapAnswer(Answer answerSrc, FullQuizEntity dst) {

        if (answerSrc == null) {
            return null;
        }

        AnswerEntity answerDst = new AnswerEntity();

        answerDst.setFullQuiz(dst);
        answerDst.setAnswerId(answerSrc.getId());
        answerDst.setCode(answerSrc.getCode());
        answerDst.setContent(answerSrc.getContent());

        return answerDst;
    }
}
