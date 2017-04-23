package ru.andrewquiz.service.mapper;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 23.04.2017.
 */

public abstract class AbstractMapper <S, D> {

    public List<D> mapList(Iterable<S> srcList) {
        if (srcList == null) {
            throw new MappingException("Cannot map from null value.");
        }

        List<D> dstList = new ArrayList<D>();

        for (S src : srcList) {
            dstList.add(map(src));
        }

        return dstList;
    }

    public D map(S src) {
        if (src == null) {
            throw new MappingException("Cannot map from null value.");
        }

        return perfomMapping(src);
    }

    public D map(S src, D dst) {
         if (src == null) {
             throw new MappingException("Cannot map from null value.");
         }

        if (dst == null) {
            throw new MappingException("Null value passed as mapping destination.");
        }

        return perfomMapping(src, dst);
    }

    protected abstract D perfomMapping(S src);

    protected abstract D perfomMapping(S src, D dst);

}
