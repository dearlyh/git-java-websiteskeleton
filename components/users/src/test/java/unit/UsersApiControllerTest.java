package unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websiteskeleton.users.UsersApiController;
import org.junit.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UsersApiControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        UsersApiController controller = new UsersApiController(new ObjectMapper());
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        String sampleJson = "[" +
            "{\"name\":\"Bob\"}," +
            "{\"name\":\"Sue\"}" +
        "]";

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().string(sampleJson))
            .andExpect(header().string("content-type", "application/json"));
    }
}