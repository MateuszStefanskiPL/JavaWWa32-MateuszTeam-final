package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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

    @Test
    void should_find_all_movies_order_by_average_score_desc(){
        //given
        moviesForTests();
        List<Movie> moviesSorted = moviesRepository.findAllByOrderByAverageScoreDesc();
        //when
        var movie1 = moviesSorted.get(0);
        var movie2 = moviesSorted.get(1);
        var movie3 = moviesSorted.get(2);
        //then
        assertEquals("A Nightmare on Elm Street", movie1.getTitle());
        assertEquals("Cry-Baby", movie2.getTitle());
        assertEquals("Sing 2", movie3.getTitle());
    }


    private void moviesForTests(){
        var movie1 = Movie.builder()
                .title("A Nightmare on Elm Street")
                .releaseDate(LocalDate.of(1984,11,9))
                .director("Wes Craven")
                .genre(Genre.HORROR)
                .starring("Elm Street in provincial Springwood is inhabited by 15-year-old Tina Gray (Amanda Wyss) - a girl who is haunted by nightmares. Their anti-hero is a man in a red and green sweater and an old hat, a scarred face with burns, and armed with a glove with four blades. The dreams become more and more realistic and one night Tina wakes up screaming to discover that her nightgown has been cut, just like in her dream.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(10.0D)
                .build();

        var movie2 = Movie.builder()
                .title("Sing 2")
                .releaseDate(LocalDate.of(2022,1,21))
                .director("Garth Jennings")
                .genre(Genre.ANIMATION)
                .starring("To perform at the Crystal Theater, Buster Moon and his crew must find and convince the rock legend to return to the stage.")
                .movieStatus(MovieStatus.PREMIERE)
                .numberOfCopies(2)
                .averageScore(8.0D)
                .build();

        var movie3 = Movie.builder()
                .title("Cry-Baby")
                .releaseDate(LocalDate.of(1990,4,6))
                .director("John Waters")
                .genre(Genre.MUSICAL)
                .starring("A social outcast(Johnny Depp) whose parents were executed in the electric chair falls in love with a girl from a good home.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(9.0D)
                .build();
        moviesRepository.saveAll(Arrays.asList(movie1,movie2,movie3));
    }

}
