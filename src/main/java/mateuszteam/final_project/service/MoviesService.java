package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dao.Movie;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.mapper.MovieMapStructMapper;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final MovieMapStructMapper movieMapper;

    public List<MovieDto> findAllMovies(){
        List<Movie> movies = (List<Movie>) moviesRepository.findAll();
        return movies.stream()
                .map(movieMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }
}
