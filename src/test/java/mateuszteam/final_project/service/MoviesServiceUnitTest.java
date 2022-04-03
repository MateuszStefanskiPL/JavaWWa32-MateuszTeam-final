package mateuszteam.final_project.service;
import mateuszteam.final_project.domain.entities.Genre;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.mapper.MoviesMapStructMapperImpl;
import mateuszteam.final_project.repository.MoviesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class MoviesServiceUnitTest {

    private static final List<MovieStatus> MS_MAIN_PAGE = Arrays.asList(MovieStatus.PREMIERE, MovieStatus.NEWEST);

    @Test
    void displays_movie_page() {
        //given
        var movieService = new MoviesService(getMoviesRepositoryMock(), new MoviesMapStructMapperImpl(),null);

        //when
        var mappedMovie = movieService.findMovieFullDescription(1L);

        //then
        Assertions.assertThat(mappedMovie).isNotNull();
        Assertions.assertThat(mappedMovie.getMovieId()).isEqualTo(1L);
        Assertions.assertThat(mappedMovie.getTitle()).isEqualTo("A Nightmare on Elm Street");
        Assertions.assertThat(mappedMovie.getReleaseDate()).isEqualTo(LocalDate.of(1984,11,9));
        Assertions.assertThat(mappedMovie.getGenre()).isEqualTo(Genre.HORROR);
        Assertions.assertThat(mappedMovie.getDirector()).isEqualTo("Wes Craven");
        //etc.

    }

    @Test
    void display_movie_tile_for_main_page() {
        //given
        var movieService = new MoviesService(getMoviesRepositoryMock(), new MoviesMapStructMapperImpl(),null);

        //when
        var mappedMovies = movieService.findMoviesForMainPage();

        //then
        Assertions.assertThat(mappedMovies).isNotNull();
        Assertions.assertThat(mappedMovies).hasSize(1);

        var mappedMovie = mappedMovies.get(0);
        Assertions.assertThat(mappedMovie).isNotNull();
        Assertions.assertThat(mappedMovie.getTitle()).isEqualTo("A Nightmare on Elm Street");
        Assertions.assertThat(mappedMovie.getReleaseDate()).isEqualTo(LocalDate.of(1984,11,9));
        Assertions.assertThat(mappedMovie.getAverageScore()).isEqualTo(10.0D);
    }

    MoviesRepository getMoviesRepositoryMock() {
        MoviesRepository moviesRepositoryMock = Mockito.mock(MoviesRepository.class);
        Mockito.when(moviesRepositoryMock.findById(1L)).thenReturn(Optional.of(getMovie1L()));
        Mockito.when(moviesRepositoryMock.findByMovieStatusIn(MS_MAIN_PAGE)).thenReturn(Arrays.asList(getMovie1L()));
        return moviesRepositoryMock;
    }

    Movie getMovie1L() {
        return Movie.builder()
                .movieId(1L)
                .title("A Nightmare on Elm Street")
                .releaseDate(LocalDate.of(1984,11,9))
                .director("Wes Craven")
                .genre(Genre.HORROR)
                .starring("Elm Street in provincial Springwood is inhabited by 15-year-old Tina Gray (Amanda Wyss) - a girl who is haunted by nightmares. Their anti-hero is a man in a red and green sweater and an old hat, a scarred face with burns, and armed with a glove with four blades. The dreams become more and more realistic and one night Tina wakes up screaming to discover that her nightgown has been cut, just like in her dream.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(10.0D)
                .build();
    }

}
