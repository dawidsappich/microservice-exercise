package de.cdiag.socialmultiplication.service;

public interface RandomGeneratorService {

    /**
     * @return a randomly-generated factor. it's always between 11 and 99
     */
    int generateRandomFactor();
}
