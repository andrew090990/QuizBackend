package ru.andrewquiz.mapper.converter.quiz;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.KeyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 22.04.2017.
 */

@Component
public class LongKeysConverter implements CustomConverter {

    @Override
    public Object convert(Object dst, Object src, Class dstClass, Class srcClass) {

        if (src == null) {
            return null;
        }

        if (!(src instanceof List)) {
            throw new MappingException("Wrong arguments in LongKeysConverter. Src must be List. Src = " + src);
        }

        List<Long> srcList = (List<Long>)src;

        List<KeyEntity> dstList = new ArrayList<KeyEntity>();

        for (Long id : srcList) {
            if (id == null || id == 0) {
                continue;
            }

            KeyEntity keyEntity = new KeyEntity();
            keyEntity.setAnswerId(id);

            dstList.add(keyEntity);
        }

        return dstList;
    }
}