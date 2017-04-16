package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dto.quiz.QuizType;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class QuizTypeLongConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof QuizType)) {
            throw new MappingException("Wrong arguments in QuizTypeLongConverter. Src must be QuizType. Src = " + src);
        }

        dst = ((QuizType)src).id();

        return dst;
    }
}
