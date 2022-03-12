package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.mapper.MoviesMapStructMapper;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesStatusChangerService {

    private static final LocalDate PREMIER_DATE = LocalDate.now().minusWeeks(2);
    private static final LocalDate NEWEST_DATE = LocalDate.now().minusMonths(3);
    private static final LocalDate STANDARD_DATE = LocalDate.now().minusYears(1);


    private final MoviesRepository moviesRepository;
    private final MoviesMapStructMapper mapper;

    @Scheduled(cron = "@daily")
    public void saveUpdatedMovies(){
        List<Movie> changedMovies = updateMoviesStatus().stream()
                .map(mapper::mapFromDtoToDomain)
                .collect(Collectors.toList());
        moviesRepository.saveAll(changedMovies);
    }

    private List<MovieDto> updateMoviesStatus(){
        var allMovies = moviesRepository.findAll().stream()
                .map(mapper::mapFromDomainToDto)
                .map(this::updateMovieStatus)
                .collect(Collectors.toList());
        return allMovies;
    }

    private MovieDto updateMovieStatus(MovieDto movie){
        if(movie.getReleaseDate().isAfter(PREMIER_DATE)) {
            changeStatus(movie,MovieStatus.PREMIERE);
        }
        else if (movie.getReleaseDate().isAfter(NEWEST_DATE) && movie.getReleaseDate().isBefore(PREMIER_DATE)){
            changeStatus(movie,MovieStatus.NEWEST);
        }
        else if (movie.getReleaseDate().isAfter(STANDARD_DATE) && movie.getReleaseDate().isBefore(NEWEST_DATE)){
            changeStatus(movie,MovieStatus.STANDARD);
        }
        else if(movie.getReleaseDate().isBefore(STANDARD_DATE)) {
            changeStatus(movie,MovieStatus.CLASSIC);
        }
        log.info("Movie " + movie.getTitle() + " status set to " + movie.getMovieStatus());
        return movie;
    }

    private void changeStatus(MovieDto movie, MovieStatus status) {
        movie.setMovieStatus(status);
    }
}
