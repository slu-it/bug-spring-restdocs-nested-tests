package withoutboot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import example.Application;


@SpringJUnitWebConfig(classes = Application.class)
@ExtendWith(RestDocumentationExtension.class)
class FailingTest {

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac) //
            .apply(documentationConfiguration(restDocumentation)) //
            .build();
    }

    @Nested
    class NestedTests {

        @Test
        void testAndDocumentation() throws Exception {
            mockMvc.perform(get("/hello"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("message", equalTo("Hello World!")))
                .andDo(document("hello-get-200"));
        }

    }

}
