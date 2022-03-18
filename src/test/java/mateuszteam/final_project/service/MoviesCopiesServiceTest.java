package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class MoviesCopiesServiceTest {

    private final MoviesCopiesService service = new MoviesCopiesService();

    @Test
    void should_return_list_of_longs_from_list_of_copies() {
        //given
        MovieCopy copy1 = MovieCopy.builder()
                .copyId(1L)
                .moviesOrder(new MoviesOrder())
                .movie(new Movie())
                .build();

        MovieCopy copy2 = MovieCopy.builder()
                .copyId(2L)
                .moviesOrder(new MoviesOrder())
                .movie(new Movie())
                .build();


    MovieCopy copy3 = MovieCopy.builder()
            .copyId(5L)
            .moviesOrder(new MoviesOrder())
            .movie(new Movie())
            .build();


    MovieCopy copy4 = MovieCopy.builder()
            .copyId(8L)
            .moviesOrder(new MoviesOrder())
            .movie(new Movie())
            .build();

        List<MovieCopy> copies = Arrays.asList(copy1,copy2,copy3,copy4);

        //when
        List<Long> longs = service.getIdsFromCopiesList(copies);
        //then

        Assertions.assertThat(longs.get(0)).isEqualTo(1L);
        Assertions.assertThat(longs.get(1)).isEqualTo(2L);
        Assertions.assertThat(longs.get(2)).isEqualTo(5L);
        Assertions.assertThat(longs.get(3)).isEqualTo(8L);


    }
            }
