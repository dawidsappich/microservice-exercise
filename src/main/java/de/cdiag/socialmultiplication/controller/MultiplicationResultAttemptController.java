package de.cdiag.socialmultiplication.controller;

import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
