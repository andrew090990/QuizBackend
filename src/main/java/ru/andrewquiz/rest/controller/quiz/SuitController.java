package ru.andrewquiz.rest.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    private SuitService suitService;

    private Logger logger = Logger.getLogger(SuitController.class);

    @Autowired
    public SuitController(SuitService suitService) {
        this.suitService = suitService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Suit> getAllSuits() {

        return suitService.getAllDtos();
    }

    @RequestMapping(value = "{suitId:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Suit getSuit(@PathVariable long suitId) {

        return suitService.getDto(suitId);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Suit postSuit(@RequestBody Suit suit) {

        Long id = suitService.createEntity(suit);

        return suitService.getDto(id);
    }

    @Transactional
    @RequestMapping(value = "{suitId:\\d+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Suit putSuit(@RequestBody Suit suit, @PathVariable long suitId) {

        suitService.updateEntity(suit, suitId);

        return suitService.getDto(suitId);
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{suitId:\\d+}", method = RequestMethod.DELETE)
    public void deleteSuit(@PathVariable long suitId) {

        suitService.deleteEntity(suitId);
    }
}
