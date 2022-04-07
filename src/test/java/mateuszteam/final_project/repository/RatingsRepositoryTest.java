package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.Rating;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class RatingsRepositoryTest {

    @Autowired
    private RatingsRepository ratingsRepository;

    @BeforeAll
    public static void initData(@Autowired RatingsRepository ratingsRepository){
        ratingsRepository.saveAll(testRatings());
    }

    @Test
    void finds_sorted_ratings_for_movie() {
        //given

        //when
        var page = ratingsRepository.findRatingsByMovie_movieIdOrderByScoreDesc(1L, PageRequest.of(0, 5));

        //then
        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getTotalElements()).isEqualTo(5);
        var ratings = page.getContent();
        Assertions.assertThat(ratings.get(0).getScore()).isEqualTo(5.0);
        Assertions.assertThat(ratings.get(4).getScore()).isEqualTo(1.0);
    }

    @Test
    void should_return_size_of_ratings_list(){
        //given
        List<Rating> ratings = ratingsRepository.findRatingsByMovie_MovieId(1L);
        List<Rating> ratings2 = (List<Rating>) ratingsRepository.findAll();

        //when
        int size = ratings.size();
        int size2 = ratings2.size();

        //then
        System.out.println("Rozmiar to -- " + size + "---------------------------------------+++++---------");
        System.out.println("Rozmiar2 to -- " + size2 + "---------------------------------------+++++---------");

        Assertions.assertThat(size).isEqualTo(5);
    }

    static List<Rating> testRatings() {
        var movie = new Movie();
        movie.setTitle("Ogniem i mieczem");

        var r4 = new Rating(movie, 2.0);
        var r5 = new Rating(movie, 1.0);
        var r1 = new Rating(movie, 5.0);
        var r2 = new Rating(movie, 4.0);
        var r3 = new Rating(movie, 3.0);


        return Arrays.asList(r1, r2, r3, r4, r5);
    }

}
