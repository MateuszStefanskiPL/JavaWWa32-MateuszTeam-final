package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.RatingDto;
import mateuszteam.final_project.domain.entities.Rating;
import mateuszteam.final_project.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RatingsRestController {

    private final RatingsService ratingsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/top5/{id}")
    public List<RatingDto> displayTop5RatingsForSingleMovie(@PathVariable Long id){
        return ratingsService.getTop5RatingsForSingleMovie(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/{id}")
    public List<RatingDto> displayAllRatingsForSingleMovieById(@PathVariable Long id){
        return ratingsService.getAllRatingsForSingleMovie(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Rating addNewRatingForMovie(@RequestBody RatingDto ratingDto){
        return ratingsService.addNewRating(ratingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/{movieId}")
    public void removeRatingsForMovie(@PathVariable Long movieId){
        ratingsService.deleteRatingsForMovie(movieId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/{ratingId}")
    public void removeRatingsByRatingId(@PathVariable Long ratingId){
        ratingsService.deleteRatingsById(ratingId);
    }

}


