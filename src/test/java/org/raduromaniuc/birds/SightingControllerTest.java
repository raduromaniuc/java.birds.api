package org.raduromaniuc.birds;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.raduromaniuc.birds.constants.ApiUrl;
import org.raduromaniuc.birds.entity.Bird;
import org.raduromaniuc.birds.entity.Sighting;
import org.raduromaniuc.birds.factory.BirdFactory;
import org.raduromaniuc.birds.factory.SightingFactory;
import org.raduromaniuc.birds.repository.BirdRepository;
import org.raduromaniuc.birds.repository.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SightingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SightingRepository sightingRepo;

    @Autowired
    private BirdRepository birdRepo;

    @Autowired
    private BirdFactory birdFactory;

    @Autowired
    private SightingFactory sightingFactory;

    @Test
    @Transactional
    public void testGetAllSightings() throws Exception {
        Bird defBird = birdRepo.save(birdFactory.createDefaultBird());

        Sighting s1 = sightingFactory.createSighting(defBird, "test-location-1", LocalDateTime.now());
        Sighting s2 = sightingFactory.createSighting(defBird, "test-location-2", LocalDateTime.now());
        sightingRepo.saveAll(Arrays.asList(s1, s2));

        mockMvc.perform(get(ApiUrl.SIGHTINGS_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(s1.getId()))
                .andExpect(jsonPath("$.content[0].location").value(s1.getLocation()))
                .andExpect(jsonPath("$.content[1].id").value(s2.getId()))
                .andExpect(jsonPath("$.content[1].location").value(s2.getLocation()));
    }

    @Test
    @Transactional
    public void testGetSightingById() throws Exception {
        Bird defBird = birdRepo.save(birdFactory.createDefaultBird());

        Sighting s1 = sightingFactory.createSighting(defBird, "test-location-1", LocalDateTime.now());
        sightingRepo.save(s1);

        mockMvc.perform(get(ApiUrl.SIGHTINGS_API_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.location").value(s1.getLocation()));
    }

    @Test
    @Transactional
    public void testCreateSighting() throws Exception {
        birdRepo.save(birdFactory.createDefaultBird());

        String sightingJson = "{\"birdId\": 1,\"location\":\"test-location\", \"dateTime\": \"2024-04-15T12:34:56.789\"}";

        mockMvc.perform(post(ApiUrl.SIGHTINGS_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sightingJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("test-location"));
    }

    @Test
    @Transactional
    public void testUpdateSighting() throws Exception {
        Bird defBird = birdRepo.save(birdFactory.createDefaultBird());

        Sighting s1 = sightingFactory.createSighting(defBird, "test-location-1", LocalDateTime.now());
        sightingRepo.save(s1);

        String sightingJson = "{\"birdId\": 1,\"location\":\"test-location-updated\", \"dateTime\": \"2024-04-15T12:34:56.789\"}";

        mockMvc.perform(put(ApiUrl.SIGHTINGS_API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sightingJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("test-location-updated"));
    }

    @Test
    @Transactional
    public void testDeleteSighting() throws Exception {
        Bird bird = birdRepo.save(birdFactory.createDefaultBird());
        sightingRepo.save(sightingFactory.createSighting(bird, "test-location-1", LocalDateTime.now()));

        mockMvc.perform(delete(ApiUrl.SIGHTINGS_API_URL + "/1"))
                .andExpect(status().isOk());

        Assertions.assertThat(sightingRepo.findAll().isEmpty()).isTrue();
    }
}
