package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.RatingDto;
import mateuszteam.final_project.domain.entities.Rating;
import mateuszteam.final_project.mapper.RatingMapStructMapper;
import mateuszteam.final_project.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RatingsRestController {

    private final RatingRepository ratingRepository;
    private final RatingMapStructMapper ratingMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RatingDto displayRatingsForSingleMovie(@PathVariable Long id){
        var rating = new Rating();
        if(ratingRepository.findById(id).isPresent()){
            rating = ratingRepository.findById(id).get();
        }
        return ratingMapper.mapFromDomainToDto(rating);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RatingDto displayTopRatings(@PathVariable long id, Pageable pageable){
        return ratingMapper.mapFromDomainToDto(
                (Rating) ratingRepository
                        .findRatingsByMovie_movieIdOrderByScoreDesc(id, pageable).get());
    }
}


