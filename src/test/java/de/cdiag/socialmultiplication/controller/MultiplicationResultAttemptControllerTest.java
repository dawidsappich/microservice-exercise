package de.cdiag.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import de.cdiag.socialmultiplication.domain.User;
import de.cdiag.socialmultiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MultiplicationResultAttemptController.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationResultAttemptControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MultiplicationService multiplicationService;

    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<MultiplicationResultAttempt> jsonResponse;

    @BeforeAll
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    void postResultReturnWrong() throws Exception {
        genericParameterizedTest(false);
    }

    private void genericParameterizedTest(boolean correct) throws Exception {
        // given
        given(multiplicationService.checkAttempt(Matchers.any(MultiplicationResultAttempt.class))).willReturn(correct);

        final User user = new User("john_doe");
        final Multiplication multiplication = new Multiplication(50, 70);
        final MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);

        // when
        final MockHttpServletResponse response = mvc.perform(post("/results").contentType(MediaType.APPLICATION_JSON).content(jsonResult.write(attempt).getJson()))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(
                new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(),correct)
        ).getJson());
    }
}