package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class MoviesStatusChangerServiceTest {

    private MoviesStatusChangerService moviesStatusChangerService = new MoviesStatusChangerService(null);

    @Test
    void should_change_movie_status(){
        //given
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

        //when
        movie1.setReleaseDate(movie1.getReleaseDate().plusYears(37));
        moviesStatusChangerService.updateMovieStatus(movie1);
        //then
        Assertions.assertEquals(MovieStatus.STANDARD, movie1.getMovieStatus());


    }


    @Test
    void should_change_statuses_in_the_list(){
        //given
        var movies = initializeTestData();
        //when
        movies.get(0).setReleaseDate(movies.get(0).getReleaseDate().plusYears(37));
        movies.get(1).setReleaseDate(movies.get(1).getReleaseDate().plusMonths(2));
        movies.get(2).setReleaseDate(movies.get(2).getReleaseDate().plusYears(31));

        var newMovies = moviesStatusChangerService.updateMoviesStatus(movies);
        //then
        Assertions.assertEquals(MovieStatus.STANDARD,movies.get(0).getMovieStatus());
        Assertions.assertEquals(MovieStatus.PREMIERE,movies.get(1).getMovieStatus());
        Assertions.assertEquals(MovieStatus.STANDARD,movies.get(2).getMovieStatus());
    }


    List<Movie> initializeTestData(){
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
                .averageScore(0.0D)
                .build();

        var movie3 = Movie.builder()
                .title("Cry-Baby")
                .releaseDate(LocalDate.of(1990,4,6))
                .director("John Waters")
                .genre(Genre.MUSICAL)
                .starring("A social outcast(Johnny Depp) whose parents were executed in the electric chair falls in love with a girl from a good home.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(0.0D)
                .build();

        return Arrays.asList(movie1,movie2,movie3);
    }




}
