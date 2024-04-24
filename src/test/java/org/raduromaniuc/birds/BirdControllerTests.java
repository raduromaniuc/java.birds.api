package org.raduromaniuc.birds;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.raduromaniuc.birds.constants.ApiUrl;
import org.raduromaniuc.birds.entity.Bird;
import org.raduromaniuc.birds.factory.BirdFactory;
import org.raduromaniuc.birds.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BirdControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BirdRepository birdRepo;

    @Autowired
    private BirdFactory birdFactory;

    @Test
    @Transactional
    public void testGetAllBirds() throws Exception {
        Bird defBird = birdFactory.createDefaultBird();
        birdRepo.save(defBird);
        birdRepo.save(defBird);

        mockMvc.perform(get(ApiUrl.BIRDS_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @Transactional
    public void testGetBirdById() throws Exception {
        Bird bird = birdRepo.save(birdFactory.createDefaultBird());

        mockMvc.perform(get(ApiUrl.BIRDS_API_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(bird.getName()))
                .andExpect(jsonPath("$.color").value(bird.getColor()))
                .andExpect(jsonPath("$.weight").value(bird.getWeight()))
                .andExpect(jsonPath("$.height").value(bird.getHeight()));
    }

    @Test
    public void testCreateBird() throws Exception {
        String birdJson = "{\"name\":\"test-name\",\"color\":\"test-color\",\"weight\":0.333,\"height\":0.777}";

        mockMvc.perform(post(ApiUrl.BIRDS_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(birdJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test-name"))
                .andExpect(jsonPath("$.color").value("test-color"))
                .andExpect(jsonPath("$.weight").value(0.333d))
                .andExpect(jsonPath("$.height").value(0.777d));
    }

    @Test
    @Transactional
    public void testUpdateBird() throws Exception {
        birdRepo.save(birdFactory.createDefaultBird());

        String birdUpdateJson = "{\"name\":\"Updated name\",\"color\":\"Updated color\",\"weight\":0.33,\"height\":0.77}";

        mockMvc.perform(put(ApiUrl.BIRDS_API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(birdUpdateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated name"))
                .andExpect(jsonPath("$.color").value("Updated color"))
                .andExpect(jsonPath("$.weight").value(0.33d))
                .andExpect(jsonPath("$.height").value(0.77d));
    }

    @Test
    @Transactional
    public void testDeleteBird() throws Exception {
        birdRepo.save(birdFactory.createDefaultBird());

        mockMvc.perform(delete(ApiUrl.BIRDS_API_URL + "/1"))
                .andExpect(status().isOk());

        Assertions.assertThat(birdRepo.findAll().isEmpty()).isTrue();
    }
}
