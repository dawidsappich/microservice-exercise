package de.cdiag.socialmultiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * this represents a Multiplication (a * b)
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Multiplication {

    private final int factorA;
    private final int factorB;

    // Empty constructor for JSON (de)serialization
    public Multiplication() {
        this(0, 0);
    }
}
