package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuestionsAnswersCorrelationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 21.04.2017.
 */

@Component
public class QuestionsAnswersCorrelationConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof List)) {
            throw new MappingException("Wrong arguments in QuestionsAnswersCorrelationTypeConverter. Src must be List. Src = " + src);
        }

        List<QuestionsAnswersCorrelationEntity> srcList = (List<QuestionsAnswersCorrelationEntity>)src;

        List<Long> dstList = new ArrayList<Long>();

        for (QuestionsAnswersCorrelationEntity questionsAnswersCorrelationEntity : srcList) {
            if (questionsAnswersCorrelationEntity == null) {
                continue;
            }

            dstList.add(questionsAnswersCorrelationEntity.getAnswerId());
        }

        return dstList;
    }
}