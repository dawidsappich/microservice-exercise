package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;

public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        final int factorA = randomGeneratorService.generateRandomFactor();
        final int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        return resultAttempt.getResultAttempt()
                == resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorA();
    }
}
