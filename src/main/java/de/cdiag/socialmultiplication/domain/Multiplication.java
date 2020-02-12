package de.cdiag.socialmultiplication.domain;

import lombok.Data;

@Data
public class Multiplication {

    private final int factorA;
    private final int factorB;
    private final int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = this.factorA * this.factorB;
    }
}
