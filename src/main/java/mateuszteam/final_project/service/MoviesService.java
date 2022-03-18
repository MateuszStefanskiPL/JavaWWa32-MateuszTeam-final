package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MovieTileDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.mapper.MoviesMapStructMapper;
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
    private final MoviesMapStructMapper movieMapper;

    public List<MovieDto> findAllMovies(){
        List<Movie> movies = (List<Movie>) moviesRepository.findAll();
        return movies.stream()
                .map(movieMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public List<MovieTileDto> findMoviesForMainPage() {
        return moviesRepository.findByMovieStatusIn(MS_MAIN_PAGE)
                .stream()
                .map(movieMapper::mapFromDomainToTileDto)
                .collect(Collectors.toList());
    }

    public MovieDto findMovieFullDescription(Long id) {
        return moviesRepository.findById(id).stream()
                .map(movieMapper::mapFromDomainToDto)
                .findFirst()
                .get();
    }

    public List<MovieTileDto> findMoviesWithHighestRating() {
        return moviesRepository.findAllByOrderByAverageScoreDesc().stream()
                .map(movieMapper::mapFromDomainToTileDto)
                .collect(Collectors.toList());
    }

    public Movie addMovie(final MovieDto movieDto) {
        return moviesRepository.save(movieMapper.mapFromDtoToDomain(movieDto));
    }
}
