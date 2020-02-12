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
    public ResponseEntity<ResultResponse> postResults(@RequestBody MultiplicationResultAttempt attempt) {
        return new ResponseEntity<>(new ResultResponse(multiplicationService.checkAttempt(attempt)), HttpStatus.OK);
    }

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    static final class ResultResponse {
        private final boolean correct;
    }
}
