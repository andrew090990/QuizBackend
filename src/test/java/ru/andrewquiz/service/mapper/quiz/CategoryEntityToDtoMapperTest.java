package ru.andrewquiz.service.mapper.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class CategoryEntityToDtoMapperTest {

    @Autowired
    private CategoryEntityToDtoMapper mapper;

    @Autowired
    private CategoryEntity categoryEntity1;

    @Autowired
    private CategoryEntity categoryEntity2;

    @Autowired
    private SuitEntity suitEntity1;

    @Autowired
    private Category category2;

    @Test
    public void testMapWhenMapSomeCategory() {

        categoryEntity2.setParentCategory(categoryEntity1);

        Category result = mapper.map(categoryEntity2);

        assertEquals("Parent category id should have been set", categoryEntity2.getParentCategory().getId(), result.getParentCategoryId());
        assertEquals("Name should have been set", categoryEntity2.getName(), result.getName());
        assertEquals("Id should have been set", categoryEntity2.getId(), result.getId());
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        Category result = mapper.map(null);
    }

}
