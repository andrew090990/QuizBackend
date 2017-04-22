package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuestionsAnswersCorrelationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 22.04.2017.
 */

@Component
public class LongQuestionsAnswersCorrelationConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof List)) {
            throw new MappingException("Wrong arguments in LongQuestionsAnswersCorrelationConverter. Src must be List. Src = " + src);
        }

        List<Long> srcList = (List<Long>)src;

        List<QuestionsAnswersCorrelationEntity> dstList = new ArrayList<QuestionsAnswersCorrelationEntity>();

        for (Long id : srcList) {
            if (id == null || id == 0) {
                continue;
            }

            QuestionsAnswersCorrelationEntity questionsAnswersCorrelationEntity = new QuestionsAnswersCorrelationEntity();
            questionsAnswersCorrelationEntity.setAnswerId(id);

            dstList.add(questionsAnswersCorrelationEntity);
        }

        return dstList;
    }
}