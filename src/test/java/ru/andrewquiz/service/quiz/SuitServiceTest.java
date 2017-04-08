package ru.andrewquiz.service.quiz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.repository.quiz.SuitRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 05.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class SuitServiceTest {

    @Autowired
    @InjectMocks
    private SuitService suitService;

    @Mock
    private SuitRepository repo;

    @Autowired
    private SuitEntity suitEntity;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuitService() {
        List<SuitEntity> list = new ArrayList<SuitEntity>();
        list.add(suitEntity);

        when(repo.findByCategoryId(anyLong())).thenReturn(list);

        List<Suit> result = suitService.getSuitsByCategoryId(1);

        Assert.assertEquals(result.get(0).getName(), suitEntity.getName());
    }
}
