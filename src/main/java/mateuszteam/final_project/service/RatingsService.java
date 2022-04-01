package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.RatingDto;
import mateuszteam.final_project.domain.entities.Rating;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.mapper.RatingsMapStructMapper;
import mateuszteam.final_project.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RatingsService {

    private final int TOP_FIVE = 5;

    private final RatingsRepository ratingsRepository;
    private final RatingsMapStructMapper ratingMapper;


    public List<RatingDto> getTop5RatingsForSingleMovie(Long id){
        Page<Rating> ratings = ratingsRepository.findRatingsByMovie_movieIdOrderByScoreDesc(id, PageRequest.of(0, TOP_FIVE));
        if(ratings.isEmpty()){
            throw new ResourceNotFoundException("Ratings", "movie id");
        }
        return ratings.stream().map(ratingMapper::mapFromDomainToDto).collect(Collectors.toList());
    }


    public List<RatingDto> getAllRatingsForSingleMovie(final Long movieId) {
        List<Rating> ratings = ratingsRepository.findRatingsByMovie_MovieId(movieId);
        if (ratings.isEmpty()){
            throw new ResourceNotFoundException("Ratings", "movie id");
        }
        return ratings.stream().map(ratingMapper::mapFromDomainToDto).collect(Collectors.toList());
    }

    public void deleteRatingsForMovie(final Long movieId) {
        var ratings = ratingsRepository.findRatingsByMovie_MovieId(movieId);
        if (ratings.isEmpty()){
            throw new ResourceNotFoundException("Ratings", "movie id");
        }
        ratingsRepository.deleteAll(ratings);
    }

    public void deleteRatingsById(final Long ratingId) {
        var rating = ratingsRepository.findById(ratingId);
        if (rating.isEmpty()){
            throw new ResourceNotFoundException("Rating", "id");
        }
        ratingsRepository.delete(rating.get());
    }

    public Rating addNewRating(final RatingDto ratingDto) {
        return ratingsRepository.save(ratingMapper.mapFromDtoToDomain(ratingDto));
    }
}
