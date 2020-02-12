package de.cdiag.socialmultiplication.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RandomGeneratorServiceTest {

    private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

    @BeforeAll
    public void setup() {
        randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
    }

    @Test
    void generateRandomFactorIsBetweenExpectedLimits() {
        // when a good sample of randomly generated factors is generated
        final List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorServiceImpl.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        // then all of them should be between 11 and 100
        assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    }
}