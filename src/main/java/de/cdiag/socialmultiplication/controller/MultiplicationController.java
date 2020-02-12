package de.cdiag.socialmultiplication.controller;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.service.MultiplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("multiplications")
public final class MultiplicationController {

    private final MultiplicationService multiplicationService;

    public MultiplicationController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("random")
    public Multiplication getrandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }
}
