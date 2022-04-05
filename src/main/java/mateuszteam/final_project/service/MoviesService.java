package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MovieTileDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.mapper.MoviesMapStructMapper;
import mateuszteam.final_project.mapper.MoviesTileMapStructMapper;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MoviesService {

    private static final List<MovieStatus> MS_MAIN_PAGE = Arrays.asList(MovieStatus.PREMIERE, MovieStatus.NEWEST);

    private final MoviesRepository moviesRepository;
    private final MoviesMapStructMapper movieMapper;
    private final MoviesTileMapStructMapper movieTileMapper;
    private final MoviesStatusChangerService statusChangerService;

    public List<MovieDto> findAllMovies(){
        var movies =  moviesRepository.findAll();
        if (movies.isEmpty()){
            throw new ResourceNotFoundException("No movies found");
        }
        return movies.stream()
                .map(movieMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public List<MovieTileDto> findMoviesForMainPage() {
        if (moviesRepository.findByMovieStatusIn(MS_MAIN_PAGE).isEmpty()){
            throw new ResourceNotFoundException("No movies found");
        }
        return moviesRepository.findByMovieStatusIn(MS_MAIN_PAGE)
                .stream()
                .map(movieTileMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public MovieDto findMovieFullDescription(Long movieId) {
        var movie = returnMovieIfAvailable(movieId);
        return movieMapper.mapFromDomainToDto(movie);
    }

    public List<MovieTileDto> findMoviesWithHighestRating() {
        if (moviesRepository.findAllByOrderByAverageScoreDesc().isEmpty()){
            throw new ResourceNotFoundException("No movies found");
        }
        return moviesRepository.findAllByOrderByAverageScoreDesc().stream()
                .map(movieTileMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public Movie addMovie(final MovieDto movieDto) {
        var movie = movieMapper.mapFromDtoToDomain(movieDto);
        movie = statusChangerService.updateMovieStatus(movie);
        return moviesRepository.save(movie);
    }

    public void deleteMovie(final Long movieId) {
        var movie = returnMovieIfAvailable(movieId);
        moviesRepository.deleteById(movie.getMovieId());
    }

    Movie returnMovieIfAvailable(Long movieId){
        var movieOptional = moviesRepository.findById(movieId);
        if (movieOptional.isEmpty()){
            throw new ResourceNotFoundException(movieId);
        }
        return movieOptional.get();
    }


}
