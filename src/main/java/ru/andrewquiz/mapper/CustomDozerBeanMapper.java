package ru.andrewquiz.mapper;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

public class CustomDozerBeanMapper extends DozerBeanMapper {

    public <T> List<T> mapList(Iterable source, Class<T> destinationClass) {
        List<T> destination = new ArrayList<T>();

        Iterator iter = source.iterator();

        while (iter.hasNext()) {
            destination.add(super.map(iter.next(), destinationClass));
        }

        return destination;
    }

}
