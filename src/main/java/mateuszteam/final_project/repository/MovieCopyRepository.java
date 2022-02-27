package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MovieCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCopyRepository extends CrudRepository<MovieCopy, Long>{

}
