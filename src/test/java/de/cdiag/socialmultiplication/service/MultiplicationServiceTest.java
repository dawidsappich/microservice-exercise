package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationServiceTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @BeforeAll
    public void setup() {
        // With this call to mocktio we tell it to process this annotations
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }


    @Test
    void createRandomMultiplicationTest() {
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        final Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
    }

    @Test
    void checkCorrectAttemptTest() {
        // given
        final Multiplication multiplication = new Multiplication(50, 50);
        final User user = new User("correctUser");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 2500);

        // when
        final boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        // then
        assertThat(attemptResult).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        final Multiplication multiplication = new Multiplication(50, 50);
        final User user = new User("correctUser");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);

        // when
        final boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        // then
        assertThat(attemptResult).isFalse();
    }
}