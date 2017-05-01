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
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.dto.quiz.Suit;
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
public class SuitDtoToEntityMapperTest {

    @Autowired
    @InjectMocks
    private SuitDtoToEntityMapper mapper;

    @Mock
    private CategoryRepository categoryRepo;

    @Autowired
    private CategoryEntity categoryEntity2;

    @Autowired
    private Suit suit1;

    @Autowired
    private Category category2;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapWhenMapSomeSuit() {

        suit1.setCategoryId(2L);

        when(categoryRepo.findOne(suit1.getCategoryId())).thenReturn(categoryEntity2);

        SuitEntity result = mapper.map(suit1);

        assertTrue("Category should have been set", result.getCategory() == categoryEntity2);
        assertEquals("Name should have been set", suit1.getName(), result.getName());
        assertTrue("Id shouldn't have been set", result.getId() == null);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        SuitEntity result = mapper.map(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testMapWhenCategoryNotFound() {

        suit1.setCategoryId(2L);

        when(categoryRepo.findOne(suit1.getCategoryId())).thenReturn(null);

        SuitEntity result = mapper.map(suit1);
    }

}
