package uk.co.which.api.forensics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.which.api.forensics.service.UserPredictionService;
import uk.co.which.api.forensics.value.Coordinate;
import uk.co.which.api.forensics.value.Position;

import java.util.Stack;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class ForensicsRestController {

    private final UserPredictionService userPredictionService;

    public ForensicsRestController(UserPredictionService userPredictionService) {
        this.userPredictionService = userPredictionService;
    }

    @GetMapping(path = "/{email}/directions", produces = APPLICATION_JSON_VALUE)
    public Stack<Position> directions(
        @PathVariable("email") String email
    ) {
        // Todo auth check for email
        return userPredictionService.getGrid().getPositions();
    }

    @GetMapping(path = "/{email}/location/{x}/{y}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> location(
        @PathVariable("email") String email,
        @PathVariable("x") int x,
        @PathVariable("y") int y
    ) {
        // Todo auth check for email
        Coordinate coordinate = new Coordinate(x, y);
        return new ResponseEntity<>(singletonMap("found", userPredictionService.locate(email, coordinate)), OK);
    }
}
