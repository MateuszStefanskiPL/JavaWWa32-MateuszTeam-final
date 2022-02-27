package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
}
