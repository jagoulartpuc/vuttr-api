package startaideia;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VuttrApplication.class)
@WebAppConfiguration
public class ToolIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(context)
                .build();
    }

    @Test
    public void toolsApiShouldReturn200toGetAllCall() throws Exception {
        mockMvc.perform(get("/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void toolsApiShouldPostNewNoteAndReturn200() throws Exception {
        String json = "    {\n" +
                "    \"name\": \"Pedro\",\n" +
                "    \"note\": 6.9\n" +
                "    }";

        mockMvc.perform(post("/note")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }
}
