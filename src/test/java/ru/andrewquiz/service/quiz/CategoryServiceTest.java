package ru.andrewquiz.service.quiz;

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
import ru.andrewquiz.rest.exception.IllegalDeletionException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Andrew on 14.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class CategoryServiceTest {

    @Autowired
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository repo;

    @Autowired
    CategoryEntity categoryEntity1;

    @Autowired
    CategoryEntity categoryEntity2;

    @Autowired
    CategoryEntity categoryEntity3;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetCategoryWhenNotFound() {

        when(repo.findOne(anyLong())).thenReturn(null);

        Category category = categoryService.getDto(1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteCategoryWhenNotFound() {

        when(repo.findOne(anyLong())).thenReturn(null);

        categoryService.deleteEntity(1L);
    }

    @Test(expected = IllegalDeletionException.class)
    public void testDeleteCategoryWhenDependentCategories() {

        when(repo.findOne(categoryEntity1.getId())).thenReturn(categoryEntity1);

       categoryService.deleteEntity(categoryEntity1.getId());
    }

    @Test(expected = IllegalDeletionException.class)
    public void testDeleteCategoryWhenDependentSuits() {

        when(repo.findOne(categoryEntity2.getId())).thenReturn(categoryEntity2);

        categoryService.deleteEntity(categoryEntity2.getId());
    }

    @Test
    public void testDeleteCategoryWhenNoDependents() {

        when(repo.findOne(categoryEntity3.getId())).thenReturn(categoryEntity3);

        categoryService.deleteEntity(categoryEntity3.getId());

        assertTrue(true);
    }
}
