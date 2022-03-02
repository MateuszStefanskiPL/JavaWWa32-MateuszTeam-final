package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.RatingDto;
import mateuszteam.final_project.domain.entities.Rating;
import mateuszteam.final_project.mapper.RatingMapStructMapper;
import mateuszteam.final_project.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class RatingsService {

    private final int TOP_FIVE = 5;

    private final RatingsRepository ratingsRepository;
    private final RatingMapStructMapper ratingMapper;


    public List<RatingDto> getTop5RatingsForSingleMovie(Long id){
        List<Rating> ratings = new ArrayList<>();
        if(!ratingsRepository.findRatingsByMovie_movieIdOrderByScoreDesc(id, PageRequest.of(0, TOP_FIVE)).isEmpty()){
            ratings = (List<Rating>) ratingsRepository.findRatingsByMovie_movieIdOrderByScoreDesc(id,PageRequest.of(0, TOP_FIVE));
        }
        return ratings.stream().map(ratingMapper::mapFromDomainToDto).collect(Collectors.toList());
    }


    public List<RatingDto> getAllRatingsForSingleMovie(final Long id) {
        List<Rating> ratings = new ArrayList<>();
        if (!ratingsRepository.findRatingsByMovie_MovieId(id).isEmpty()){
            ratings = ratingsRepository.findRatingsByMovie_MovieId(id);
        }
        return ratings.stream().map(ratingMapper::mapFromDomainToDto).collect(Collectors.toList());
    }
}
