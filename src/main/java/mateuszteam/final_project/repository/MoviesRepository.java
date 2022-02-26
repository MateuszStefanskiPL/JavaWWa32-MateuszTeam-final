package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

}
