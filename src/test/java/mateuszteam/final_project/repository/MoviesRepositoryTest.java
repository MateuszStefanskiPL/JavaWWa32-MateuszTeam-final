package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MoviesRepositoryTest {

    @Autowired
    private MoviesRepository moviesRepository;

    @Test
    public void saves_and_loads_movie() {

        //given
        Movie m1 = new Movie();
        String title = "Film title";
        m1.setTitle(title);
        m1.setDirector("Film director");
        m1.setMovieStatus(MovieStatus.PREMIERE);
        m1.setGenre(Genre.ANIMATION);
        m1.setStarring("Actors");
        m1.setAverageScore(0.0D);

        Optional<Movie> foundMovieOptional = moviesRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isEmpty()).isTrue();

        //when
        moviesRepository.save(m1);
        foundMovieOptional = moviesRepository.findByTitle(title);

        //then
        Assertions.assertThat(foundMovieOptional.isPresent()).isTrue();
        Movie foundMovie = foundMovieOptional.get();
        Assertions.assertThat(foundMovie.getTitle()).isEqualTo(m1.getTitle());
        Assertions.assertThat(foundMovie.getReleaseDate()).isEqualTo(m1.getReleaseDate());
    }

}
