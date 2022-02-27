package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MainPageMovieDto;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.mapper.MovieMapStructMapper;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesService {

    private static final List<MovieStatus> MS_MAIN_PAGE = Arrays.asList(MovieStatus.PREMIERE, MovieStatus.NEWEST);

    private final MoviesRepository moviesRepository;
    private final MovieMapStructMapper movieMapper;

    public List<MovieDto> findAllMovies(){
        List<Movie> movies = (List<Movie>) moviesRepository.findAll();
        return movies.stream()
                .map(movieMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public List<MainPageMovieDto> findMoviesForMainPage() {
        return moviesRepository.findByMovieStatusIn(MS_MAIN_PAGE)
                .stream()
                .map(movieMapper::mapFromDomainToMainPageMovieDto)
                .collect(Collectors.toList());
    }

    public MovieDto showMovieFullDescription(Long id) {
        return moviesRepository.findById(id).stream()
                .map(m -> movieMapper.mapFromDomainToDto(m))
                .findFirst()
                .get();
    }
}
