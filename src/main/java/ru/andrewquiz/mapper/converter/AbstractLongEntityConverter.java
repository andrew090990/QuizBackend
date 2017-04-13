package ru.andrewquiz.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.rest.exception.EntityNotFoundException;

/**
 * Created by Andrew on 12.04.2017.
 */

@Component
public abstract class AbstractLongEntityConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof Long)) {
            throw new MappingException("Wrong arguments in AbstractLongEntityConverter. Src must be Long. Src = " + src);
        }
        Long id = (Long)src;

        dst = convertLongToEntity(id);

        if (dst == null) {
            throw new EntityNotFoundException(dstClass, id);
        }

        return dst;
    }

    protected abstract Object convertLongToEntity(Long id);
}