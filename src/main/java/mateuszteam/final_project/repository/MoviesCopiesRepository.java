package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MovieCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesCopiesRepository extends CrudRepository<MovieCopy, Long>{

    List<MovieCopy> findMovieCopiesByMovie_MovieId(Long id);
}
