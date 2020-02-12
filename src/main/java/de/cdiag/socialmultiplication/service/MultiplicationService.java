package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;

public interface MultiplicationService {

    /**
     * Creates a Multiplication Object with two random factors
     * between 11 and 99
     * @return a Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the attempt matches the result of the multiplication, otherwise false
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
