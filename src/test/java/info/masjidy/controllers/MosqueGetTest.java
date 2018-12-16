package info.masjidy.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import info.masjidy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@DataJpaTest
@EnableWebMvc
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ComponentScan(basePackages = { "info.masjidy" })
public class MosqueGetTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenMosques_thenGetAllMosques() throws Exception {
        mvc.perform(get("/public/mosque/all"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.mosques.length()", is(5)))
                .andExpect(jsonPath("$.mosques[0].name", is("Muslim Student House")));
    }

    @Test
    public void whenMosquesWithPrayers_thenGetOne() throws Exception {
        mvc.perform(get("/public/mosque/all?with_prayers_only=true"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.mosques.length()", is(1)))
                .andExpect(jsonPath("$.mosques[0].name", is("Amanah Mosque")));
    }

    @Test
    public void whenMosque1_thenGetMosqueById() throws Exception {
        mvc.perform(get("/public/get/mosque?id=1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Muslim Student House")));
    }

    @Test
    public void whenFindMosque_thenGetMosques() throws Exception {
        mvc.perform(get("/public/mosque/find?lat=52.465322&lng=-1.937922&radius_km=5"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.mosques.length()", is(3)))
                .andExpect(jsonPath("$.mosques[0].mosque.name", is("Birmingham Central Mosque")))
                .andExpect(jsonPath("$.mosques[1].mosque.name", is("Muslim Student House")))
                .andExpect(jsonPath("$.mosques[2].mosque.name", is("Amanah Mosque")));
    }

    @Test
    public void whenFindMosqueWithPrayers_thenGetOne() throws Exception {
        mvc.perform(get("/public/mosque/find?lat=52.465322&lng=-1.937922&radius_km=5&with_prayers_only=true"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.mosques.length()", is(1)))
                .andExpect(jsonPath("$.mosques[0].mosque.name", is("Amanah Mosque")));
    }
}
