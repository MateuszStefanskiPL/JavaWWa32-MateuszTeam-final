package mateuszteam.final_project.service;

import mateuszteam.final_project.exceptions.CopiesNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SessionCartServiceTest {

    @Autowired
    SessionCartService service;

    @Test
    void should_get_copy_by_movie_id(){
        //given
        var movieId = 1L;
        //vhen
        var copy = service.getFreeCopyForMovieId(movieId);
        //then
        assertNotNull(copy.getCopy());
    }

    @Test
    void should_throw_copies_not_found_exception(){
        //given
        var movieId = 5L;

        //when  //then
        assertThrows(CopiesNotFoundException.class, () -> {
            var copy = service.getFreeCopyForMovieId(movieId);
        });
    }

}
