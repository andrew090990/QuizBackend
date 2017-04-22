package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.KeyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 21.04.2017.
 */

@Component
public class KeysConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof List)) {
            throw new MappingException("Wrong arguments in KeysTypeConverter. Src must be List. Src = " + src);
        }

        List<KeyEntity> srcList = (List<KeyEntity>)src;

        List<Long> dstList = new ArrayList<Long>();

        for (KeyEntity keyEntity : srcList) {
            if (keyEntity == null) {
                continue;
            }

            dstList.add(keyEntity.getAnswerId());
        }

        return dstList;
    }
}