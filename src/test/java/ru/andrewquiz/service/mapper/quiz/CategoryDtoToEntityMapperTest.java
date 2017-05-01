package ru.andrewquiz.service.mapper.quiz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class CategoryDtoToEntityMapperTest {

    @Autowired
    @InjectMocks
    private CategoryDtoToEntityMapper mapper;

    @Mock
    private CategoryRepository repo;

    @Autowired
    private CategoryEntity categoryEntity1;

    @Autowired
    private Category category2;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapWhenMapSomeCategory() {

        category2.setParentCategoryId(1L);

        when(repo.findOne(category2.getParentCategoryId())).thenReturn(categoryEntity1);

        CategoryEntity result = mapper.map(category2);

        assertTrue("Parent category should have been set", result.getParentCategory() == categoryEntity1);
        assertEquals("Name should have been set", category2.getName(), result.getName());
        assertTrue("Id shouldn't have been set", result.getId() == null);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        CategoryEntity result = mapper.map(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testMapWhenParentCategoryNotFound() {

        category2.setParentCategoryId(1L);

        when(repo.findOne(category2.getParentCategoryId())).thenReturn(null);

        CategoryEntity result = mapper.map(category2);
    }

}
