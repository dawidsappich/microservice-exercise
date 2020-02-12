package de.cdiag.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cdiag.socialmultiplication.domain.Multiplication;
import de.cdiag.socialmultiplication.service.MultiplicationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(MultiplicationController.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Multiplication> json;

    @BeforeAll
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getRandomMultiplicationTest() throws Exception {
        // given
        given(multiplicationService.createRandomMultiplication())
                .willReturn(new Multiplication(70, 20));

        // when
        final MockHttpServletResponse response = mockMvc.perform(get("/multiplications/random")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        // then
        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(json.write(new Multiplication(70, 20)).getJson());
    }
}