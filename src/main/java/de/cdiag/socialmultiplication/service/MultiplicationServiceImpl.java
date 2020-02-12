package de.cdiag.socialmultiplication.service;

import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.domain.User;
import de.cdiag.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import de.cdiag.socialmultiplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService, MultiplicationResultAttemptRepository attemptRepository, UserRepository userRepository) {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        final int factorA = randomGeneratorService.generateRandomFactor();
        final int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        // avoid hacks
        Assert.isTrue(!attempt.isCorrect(), "you cant send an attempt marked as 'correct'");

        final Optional<User> userByAlias = userRepository.findByAlias(attempt.getUser().getAlias());

        final boolean correct = attempt.getResultAttempt()
                == attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();

        // set the values accordingly
        final MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(userByAlias.orElse(attempt.getUser()), attempt.getMultiplication(), attempt.getResultAttempt(), correct);

        attemptRepository.save(checkedAttempt);

        return correct;

    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findByUserAliasOrderByIdDesc(userAlias);
    }
}
