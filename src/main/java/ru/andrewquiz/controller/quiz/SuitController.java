package ru.andrewquiz.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.service.quiz.SuitService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Andrew on 23.03.2017.
 */

@Controller
@RequestMapping("/suits")
public class SuitController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Suit> getAllSuits() {

        return suitService.getSuits();
    }

    @RequestMapping(value = "{suitId:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Suit getSuit(@PathVariable long suitId) {

        return suitService.getSuit(suitId);
    }


    @Autowired
    private SuitService suitService;

    private Logger logger = Logger.getLogger(SuitController.class);

}
