package mateuszteam.final_project.service;

import mateuszteam.final_project.repository.RatingsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingsServiceTest {

    @Autowired
    RatingsRepository ratingsRepository;

    @Autowired
    RatingsService ratingsService;

    @Test
    void should_return_all_ratings_for_movie(){

        var ratings = ratingsService.getAllRatingsForSingleMovie(1L);

        Assertions.assertThat(ratings.size()).isEqualTo(2);
    }




}
