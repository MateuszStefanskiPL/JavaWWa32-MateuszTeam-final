package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MovieTileDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class MoviesRestController {


    private final MoviesService moviesService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/main")
    public List<MovieTileDto> displayMoviesTilesForMainPage(){
        return moviesService.findMoviesForMainPage();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MovieDto displayMovieFullDescription(@PathVariable Long id){
        return  moviesService.findMovieFullDescription(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<MovieDto> displayAllMovies(){
        return  moviesService.findAllMovies();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/best")
    public List<MovieTileDto> displayMoviesWithHighestRating(){
        return moviesService.findMoviesWithHighestRating();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/newmovie")
    public Movie addNewMovie(@RequestBody MovieDto movieDto){
       return moviesService.addMovie(movieDto);
    }






}
