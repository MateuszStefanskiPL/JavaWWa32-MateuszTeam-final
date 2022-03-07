package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesCopiesRepository extends JpaRepository<MovieCopy, Long> {

    List<MovieCopy> findMovieCopiesByMovie_MovieId(Long id);
    Movie findMovieByCopyId(Long id);
    List<MovieCopy> findAllByMovie_movieId(Long movieId);
    MovieCopy findOneByMovie_movieIdAndMoviesOrder_OrderIdIsNull(Long movieId);
}
