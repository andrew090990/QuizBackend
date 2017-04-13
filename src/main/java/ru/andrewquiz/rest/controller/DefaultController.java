package ru.andrewquiz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.andrewquiz.rest.exception.UnknownResourceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew on 09.04.2017.
 */

@Controller
public class DefaultController {

    @RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request) {

        throw new UnknownResourceException(request.getRequestURI());
    }
}
