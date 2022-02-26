package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
