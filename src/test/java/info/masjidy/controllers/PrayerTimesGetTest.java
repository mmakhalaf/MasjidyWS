package info.masjidy.controllers;

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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@DataJpaTest
@EnableWebMvc
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ComponentScan(basePackages = { "info.masjidy" })
@ContextConfiguration
public class PrayerTimesGetTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenPrayerTimesById_thenReturnNothing() throws Exception {

        mvc.perform(get("/public/prayer_time/mosque_id?id=1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(0)));
    }

    @Test
    public void whenPrayerTimesByMosqueId_thenReturnAll() throws Exception {

        mvc.perform(get("/public/prayer_time/mosque_id?id=3"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(31*4)));
    }

    @Test
    public void whenPrayerTimesByInvalidMosqueId_thenReturnNothing() throws Exception {

        mvc.perform(get("/public/prayer_time/mosque_id?id=30"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(0)));
    }

    @Test
    public void whenPrayerTimesByRangeUnique_thenReturnTwo() throws Exception {

        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0615&endMonthDay=0616&year=2018"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(2)));
    }

    @Test
    public void whenPrayerTimesByRange_thenReturnNone() throws Exception {
        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0715&endMonthDay=0716&year=2017"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(0)));
    }

    @Test
    public void whenPrayerTimesByRangeDuplicate_thenRemoveReturnTwo() throws Exception {

        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0515&endMonthDay=0516&year=2017"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(2)))
                .andExpect(jsonPath("$.prayerTimes[0].year", is(2017)))
                .andExpect(jsonPath("$.prayerTimes[1].year", is(2017)));

        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0515&endMonthDay=0516&year=2018"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(2)))
                .andExpect(jsonPath("$.prayerTimes[0].year", is(2018)))
                .andExpect(jsonPath("$.prayerTimes[1].year", is(2018)));
    }


    @Test
    public void whenPrayerTimesByRangeOld_thenRemoveReturnTwo() throws Exception {

        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0415&endMonthDay=0416&year=2017"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(2)))
                .andExpect(jsonPath("$.prayerTimes[0].year", is(2017)))
                .andExpect(jsonPath("$.prayerTimes[1].year", is(2017)));

        mvc.perform(get("/public/prayer_time/range?id=3&startMonthDay=0415&endMonthDay=0416&year=2017"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.prayerTimes.length()", is(2)))
                .andExpect(jsonPath("$.prayerTimes[0].year", is(2017)))
                .andExpect(jsonPath("$.prayerTimes[1].year", is(2017)));
    }
}
