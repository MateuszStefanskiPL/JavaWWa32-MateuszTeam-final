package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class MoviesRestController {


    private final MoviesService moviesService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<MovieDto> displayAllMovies(){
        return moviesService.findAllMovies();
    }

}
