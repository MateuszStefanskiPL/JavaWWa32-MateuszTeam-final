package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dao.Movie;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class MoviesService {


    private final MoviesRepository moviesRepository;


    public Set<Movie> findAllMovies(){
        return (Set<Movie>) moviesRepository.findAll();
    }
}
