package de.cdiag.socialmultiplication.controller;

import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.service.MultiplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("results")
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    public ResponseEntity<MultiplicationResultAttempt> postResults(@RequestBody MultiplicationResultAttempt attempt) {
        final boolean isCorrect = multiplicationService.checkAttempt(attempt);
        final MultiplicationResultAttempt checkedAttempt
                = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);
        return new ResponseEntity<>(checkedAttempt, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return new ResponseEntity<>(multiplicationService.getStatsForUser(alias), HttpStatus.OK);
    }

}
