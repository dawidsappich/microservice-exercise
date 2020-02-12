package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
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
        final boolean correct = resultAttempt.getResultAttempt()
                == resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB();

        // avoid hacks
        Assert.isTrue(!resultAttempt.isCorrect(), "you cant send an attempt marked as 'correct'");

        // set the values accordingly
        final MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(resultAttempt.getUser(), resultAttempt.getMultiplication(), resultAttempt.getResultAttempt(), correct);

        return correct;

    }
}
