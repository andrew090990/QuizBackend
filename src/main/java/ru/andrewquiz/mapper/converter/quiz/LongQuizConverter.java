package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dto.quiz.QuizType;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class LongQuizConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof Long)) {
            throw new MappingException("Wrong arguments in LongQuizTypeConverter. Src must be Long. Src = " + src);
        }

        dst = QuizType.fromId((long)src);

        if (dst == null) {
            throw new MappingException("Couldn't map id " + src + " to quiz type.");
        }

        return dst;
    }
}
