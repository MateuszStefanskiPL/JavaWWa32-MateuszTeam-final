package mateuszteam.final_project.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MainPageMovieDto;
import mateuszteam.final_project.domain.dto.MovieDto;
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
    public List<MainPageMovieDto> displayMoviesForMainPage(){
        return moviesService.findMoviesForMainPage();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MovieDto displayMovieFullDescription(@PathVariable Long id){
        return  moviesService.showMovieFullDescription(id);
    }






}
