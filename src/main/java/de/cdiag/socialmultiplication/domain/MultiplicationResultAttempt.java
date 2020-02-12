package de.cdiag.socialmultiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Identifies an attempt from the {@link User} to solve a {@link Multiplication}
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class MultiplicationResultAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;
    private final boolean correct;

    // Empty constructor for JSON (de)serialization
    public MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }
}
