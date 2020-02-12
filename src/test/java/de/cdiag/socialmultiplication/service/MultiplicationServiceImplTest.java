package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.domain.User;
import de.cdiag.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import de.cdiag.socialmultiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @BeforeAll
    public void setup() {
        // With this call to mocktio we tell it to process this annotations
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
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
        final Multiplication multiplication = new Multiplication(50, 60);
        final User user = new User("john_doe");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        final MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());

        // when
        final boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        // then
        assertThat(attemptResult).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        final Multiplication multiplication = new Multiplication(50, 50);
        final User user = new User("correctUser");
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());

        // when
        final boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        // then
        assertThat(attemptResult).isFalse();
        verify(attemptRepository).save(attempt);
    }

    @Test
    void retrieveStats() {
        // given
        final User user = new User("john_doe");

        final Multiplication multiplication = new Multiplication(50, 60);
        final MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        final MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3020, false);

        final ArrayList<MultiplicationResultAttempt> attempts = Lists.newArrayList(attempt1, attempt2);
        given(attemptRepository.findByUserAliasOrderByIdDesc("john_doe"))
                .willReturn(attempts);

        // when
        final List<MultiplicationResultAttempt> latestAttemptResults = multiplicationServiceImpl.getStatsForUser("john_doe");

        // then
        assertThat(latestAttemptResults).isEqualTo(attempts);
    }
}