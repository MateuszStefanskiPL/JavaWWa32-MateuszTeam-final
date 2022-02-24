package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.dao.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, Long> {
}
