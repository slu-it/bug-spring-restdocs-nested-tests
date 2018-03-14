package example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
class AlsoFailingTest {

    @Autowired
    MockMvc mockMvc;

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @AutoConfigureRestDocs
    class NestedTests {

        @Autowired
        MockMvc mockMvc;

        @Test
        void testAndDocumentation() throws Exception {
            mockMvc.perform(get("/hello"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("message", equalTo("Hello World!")))
                    .andDo(document("hello-get-200"));
        }

    }

}
