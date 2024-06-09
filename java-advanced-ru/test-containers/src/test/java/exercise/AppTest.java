package exercise;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Transactional
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @BeforeAll
    static void setUp() {
        database.start();
    }

    @AfterAll
    static void cleanUp() {
        database.stop();
    }

    @BeforeEach
    void testIsContainerRunning() {
        assertTrue(database.isRunning());
    }

    @Test
    @Order(1)
    void testGetAllPerson() throws Exception {
        MockHttpServletResponse responseGet = mockMvc
                .perform(
                        get("/people").contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        assertThat(responseGet.getStatus()).isEqualTo(200);
        assertThat(responseGet.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(responseGet.getContentAsString()).hasSize(193);
    }
    // END

    @Test
    @Order(2)
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    @Order(3)
    void testDeletePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people/4"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Robert", "Lock");

        MockHttpServletResponse responsePost = mockMvc
                .perform(
                        delete("/people/4")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        response = mockMvc
                .perform(get("/people/4"))
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    @Order(4)
    void testShowPerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people/3"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jassica", "Simpson");
    }

    @Test
    @Order(5)
    void testUpdatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
                .perform(
                        patch("/people/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"firstName\": \"Duke\", \"lastName\": \"Nukem\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
                .perform(get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Duke", "Nukem");
    }
}
