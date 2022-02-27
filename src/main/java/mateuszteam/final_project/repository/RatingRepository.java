package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

    Page<Rating> findRatingsByMovie_movieIdOrderByScoreDesc(Long movieId, Pageable pageable);

}
