package startaideia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
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
import startaideia.domain.Tool;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VuttrApplication.class)
@WebAppConfiguration
public class ToolIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private String id;

    @Before
    public void setupAndPostToolBeforeAll() throws Exception {
        mockMvc = webAppContextSetup(context)
                .build();
        id = UUID.randomUUID().toString();
        Tool tool = new Tool();
        tool.setId(id);
        tool.setTitle("hotel");
        tool.setDescription("Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.");
        tool.setTags(Arrays.asList("node",
                "organizing",
                "webapps",
                "domain",
                "developer",
                "https",
                "proxy"));

        String json = objectMapper.writeValueAsString(tool);
        mockMvc.perform(post("/tools")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void toolsApiShouldListAllAndReturn200() throws Exception {
        mockMvc.perform(get("/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(status().is(200));
    }

    @Test
    public void toolsApiShouldListByTagAndReturn200WithOneTool() throws Exception {
        mockMvc.perform(get("/tools/tag=node")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(status().is(200));
    }

    @Test
    public void toolsApiShouldListByTagAndReturn200WithNoTool() throws Exception {
        mockMvc.perform(get("/tools/tag=abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0))
                .andExpect(status().is(200));
    }

    @After
    public void toolsApiShouldRemoveAToolByIdAndReturn204AfterAll() throws Exception {
        mockMvc.perform(delete("/tools/" + id)
                .contentType("application/json"))
                .andExpect(status().isNoContent());
    }
}