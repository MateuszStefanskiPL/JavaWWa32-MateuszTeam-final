package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RatingsRepository extends CrudRepository<Rating, Long> {

    //todo test it
    Page<Rating> findRatingsByMovie_movieIdOrderByScoreDesc(Long movieId, Pageable pageable);

    List<Rating> findRatingsByMovie_MovieId(Long movieId);

}
