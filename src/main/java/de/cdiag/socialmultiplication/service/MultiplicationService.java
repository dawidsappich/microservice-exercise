package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;

public interface MultiplicationService {

    /**
     * Creates a Multiplication Object with two random factors
     * between 11 and 99
     * @return a Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();
}
