package ru.andrewquiz.rest.controller.auth;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.service.auth.UserService;

import javax.ws.rs.core.MediaType;

/**
 * Created by Andrew on 23.03.2017.
 */

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody void postUser(@RequestBody User user) {

        Long id = userService.createEntity(user);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody void putSuit(@RequestBody User user) {

        userService.updateCurrentUser(user);
    }
}
