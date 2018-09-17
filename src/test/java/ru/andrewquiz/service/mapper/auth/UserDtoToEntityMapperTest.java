package ru.andrewquiz.service.mapper.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class UserDtoToEntityMapperTest {

    @Autowired
    private UserDtoToEntityMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Test
    public void testMapWhenMapSomeSuit() {

        User user1 = new User();

        user1.setUserName("Test user");
        user1.setPassword("pass123");

        UserEntity result = mapper.map(user1);

        assertTrue("Password doesn't match", bcryptEncoder.matches(user1.getPassword(), result.getPassword()));
        assertEquals("Name should have been set", user1.getUserName(), result.getUserName());
        assertTrue("Id shouldn't have been set", result.getId() == null);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        UserEntity result = mapper.map(null);
    }
}
